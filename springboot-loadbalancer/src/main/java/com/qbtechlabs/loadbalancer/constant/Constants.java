package com.qbtechlabs.loadbalancer.constant;

public class Constants {
    public static final String URI_ALL = "/**";
    public static final String URI_HEALTH = "/health";
    public static final double TEN_LAC = 1_000_000.0;
    public static final String LIVE_SERVERS_PROVIDED = "liveServersProvided";
    public static final String BACKEND_SERVERS = "backendservers";
    /******************************Loadbalancer Strategies**********************************/
    public static final String STRATEGY_ROUND_ROBIN = "roundrobin";
    public static final String STRATEGY_RANDOM = "random";
    public static final String STRATEGY_WEIGHTED_ROUND_ROBIN = "weightedroundrobin";
    public static final String STRATEGY_LEAST_CONNECTIONS = "leastconnections";


    /******************************Meassages**********************************/
    public static final String  CHECKING_SERVER_HEALTH= "****** Checking health for servers ......";
    public static final String  UP = "UP";
    public static final String MSG_CONFIGURING_TOMCAT_VIRTUAL_THREADS = "****** Configuring Tomcat to use virtual threads for better concurrency handling ******";
    public static final String MSG_LOADBALANCER_HANDLED = "****** Loadbalancer Request processed sucessfully, timetaken [{}] ms ******";
    public static final String MSG_UNKNOWN_STRATEGY = "Unknown load balancing strategy: ";
    public static final String MSG_USING_STRATEGY = " ****** Using load balancing strategy: [{}] ******";
    public static final String MSG_FORWARDING_REQUEST = "****** Forwarding request to servers with method: [{}], URI: [{}], headers: [{}], body: [{}], servers: [{}] ******";
    public static final String MSG_SELECTED_SERVER = " ****** Selected server: [{}] ******";
    public static final String MSG_REQUEST_FWD_TO = "Request forwarded to ";
    public static final String MSG_WITH_METHOD = " with method: ";
    public static final String MSG_COMMA_URI = ", URI: ";
    public static final String MSG_SERVER_LIST_CNNOT_BE_EMPTY= "Server list cannot be empty. Please configure servers in the application.yml file.";
    public static final String MSG_INIT_SERVERS = "****** Initializing servers : [{}] ******";
    public static final String MSG_NO_SERVER_AVL = "No available servers to handle the request";
}
