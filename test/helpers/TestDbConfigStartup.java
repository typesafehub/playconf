package helpers;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;

public class TestDbConfigStartup implements ServerConfigStartup {
    @Override
    public void onStart(ServerConfig serverConfig) {
        serverConfig.setDefaultServer(true);
    }
}
