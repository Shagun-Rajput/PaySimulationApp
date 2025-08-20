package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shagun.rajput
 */
@Service
public final class WeightedRoundRobinStrategy implements CommonLoadBalancingStrategy {
    private int currentIndex = NumberEnum.MINUS_ONE.value();
    private int currentWeight = NumberEnum.ZERO.value();
    private int gcdWeight = NumberEnum.ZERO.value();
    private int maxWeight = NumberEnum.ZERO.value();
    /*******************************************************************************************************************
     * End-to-End Flow of Weighted Round Robin Strategy:
     *
     * 1. **Initialization**:
     *    - The `WeightedRoundRobinStrategy` class is initialized with default values for `currentIndex`, `currentWeight`,
     *      `gcdWeight`, and `maxWeight`.
     *    - These values are used to track the state of the load balancing algorithm.

     * 2. **Server Selection**:
     *    - The `selectServer` method is invoked with a list of servers.
     *    - If `gcdWeight` is zero (indicating weights are not yet calculated), the `calculateWeights` method is called:
     *        - `maxWeight`: Determines the maximum weight among all servers using a parallel stream.
     *        - `gcdWeight`: Calculates the greatest common divisor (GCD) of all server weights using the Euclidean algorithm.
     *    - The `findNextServer` method is then called to select the next server based on the Weighted Round Robin algorithm.

     * 3. **Finding the Next Server**:
     *    - The `findNextServer` method iterates through the list of servers in a loop:
     *        - **Update Current Index**:
     *            - The `updateCurrentIndex` method increments the `currentIndex` and wraps it around to the start of the list
     *              when it reaches the end.
     *        - **Check for New Weight Cycle**:
     *            - The `isNewWeightCycle` method checks if the `currentIndex` has wrapped back to zero, indicating a new
     *              weight cycle.
     *        - **Update Current Weight**:
     *            - The `updateCurrentWeight` method reduces the `currentWeight` by the `gcdWeight`.
     *            - If the `currentWeight` becomes zero or negative, it is reset to the `maxWeight`.
     *        - **Check Weight Exhaustion**:
     *            - The `isWeightExhausted` method checks if the `currentWeight` has reached zero, indicating no eligible
     *              server can be selected in the current cycle.
     *        - **Check Server Eligibility**:
     *            - The `isServerEligible` method checks if the current server's weight is greater than or equal to the
     *              `currentWeight`.
     *            - If the server is eligible, it is returned as the selected server.

     * 4. **Returning the Selected Server**:
     *    - The selected server is returned to the caller of the `selectServer` method.
     *    - If no eligible server is found (e.g., all servers have zero weight), the method returns `null`.

     * 5. **Key Methods**:
     *    - `calculateWeights`: Computes the maximum weight and GCD of server weights.
     *    - `gcd`: Implements the Euclidean algorithm to calculate the GCD of two numbers.
     *    - `updateCurrentIndex`, `isNewWeightCycle`, `updateCurrentWeight`, `isWeightExhausted`, `isServerEligible`:
     *      Helper methods to modularize and simplify the logic of the Weighted Round Robin algorithm.

     * 6. **Thread Safety**:
     *    - The algorithm assumes that the list of servers and their weights are immutable during the selection process.
     *    - If the server list or weights are updated dynamically, additional synchronization may be required.

     * 7. **Performance**:
     *    - The use of parallel streams in `calculateWeights` ensures efficient computation of `maxWeight` and `gcdWeight`
     *      for large server lists.
     *    - The modular design of helper methods improves code readability and maintainability.
     ******************************************************************************************************************/
    @Override
    public Server selectServer(List<Server> servers) {
        if (gcdWeight == NumberEnum.ZERO.value()) {
            calculateWeights(servers);
        }
        return findNextServer(servers);
    }
    /*******************************************************************************************************************
     * Finds the next server based on the Weighted Round Robin strategy.
     ******************************************************************************************************************/
    private Server findNextServer(List<Server> servers) {
        while (true) {
            updateCurrentIndex(servers.size());
            if (isNewWeightCycle()) {
                updateCurrentWeight();
                if (isWeightExhausted()) {
                    return null;
                }
            }
            Server server = servers.get(currentIndex);
            if (isServerEligible(server)) {
                return server;
            }
        }
    }
    /*******************************************************************************************************************
     * Updates the current index to point to the next server in the list.
     ******************************************************************************************************************/
    private void updateCurrentIndex(int serverCount) {
        currentIndex = (currentIndex + NumberEnum.ONE.value()) % serverCount;
    }
    /*******************************************************************************************************************
     * Checks if the current index is at the beginning of the server list.
     ******************************************************************************************************************/
    private boolean isNewWeightCycle() {
        return currentIndex == NumberEnum.ZERO.value();
    }
    /*******************************************************************************************************************
     * Updates the current weight by subtracting the GCD weight.
     ******************************************************************************************************************/
    private void updateCurrentWeight() {
        currentWeight -= gcdWeight;
        if (currentWeight <= NumberEnum.ZERO.value()) {
            currentWeight = maxWeight;
        }
    }
    /*******************************************************************************************************************
     * Checks if the current weight has been exhausted.
     ******************************************************************************************************************/
    private boolean isWeightExhausted() {
        return currentWeight == NumberEnum.ZERO.value();
    }
    /*******************************************************************************************************************
     * Checks if the server is eligible for selection based on its weight.
     * A server is eligible if its weight is greater than or equal to the current weight.
     ******************************************************************************************************************/
    private boolean isServerEligible(Server server) {
        return server.getWeight() >= currentWeight;
    }
    /*******************************************************************************************************************
     * Calculates the maximum weight and greatest common divisor (GCD) of the weights of the servers.
     ******************************************************************************************************************/
    private void calculateWeights(List<Server> servers) {
        maxWeight = servers.parallelStream()
                .mapToInt(Server::getWeight)
                .max()
                .orElse(NumberEnum.ZERO.value());
        gcdWeight = servers.parallelStream()
                .mapToInt(Server::getWeight)
                .reduce(NumberEnum.ZERO.value(), this::gcd);
    }

    /*******************************************************************************************************************
     * Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     * This method is used to determine the GCD of server weights for the Weighted Round Robin strategy.
     ******************************************************************************************************************/
    private int gcd(int firstNumber, int secondNumber) {
        return (secondNumber == NumberEnum.ZERO.value())
                ? firstNumber
                : gcd(secondNumber, firstNumber % secondNumber);
    }
}