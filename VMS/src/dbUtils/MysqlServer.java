package dbUtils;
public class MysqlServer {
    private String address="localhost";
    private String port="3306";
    private String database="vms";
    private String username="root"; //数据库登录名
    private String password="wm1234567890";   //数据库登录密码;
    private String URL="";
    public MysqlServer()
    {
        setPort("3306");
        setDatabase("vms");
        setUsername("root");
        setPassword("wm1234567890");
        setURL("jdbc:mysql://"+address+":"+port+"/"+database);
    }
    public MysqlServer(String address,
                       String port,
                       String database,
                       String username,
                       String password
    ) {
        this.setAddress(address);
        this.setPort(port);
        this.setDatabase(database);
        this.setUsername(username);
        this.setPassword(password);
        this.setURL("jdbc:mysql://"+address+":"+port+"//"+database);
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public String getURL() {
        return URL;
    }
    private  void setURL(String URL) {
        this.URL = URL;
    }
}