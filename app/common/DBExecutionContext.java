package common;

import play.libs.Akka;
import scala.concurrent.ExecutionContext;

public class DBExecutionContext {
    public static ExecutionContext ctx = Akka.system().dispatchers().lookup("akka.db-dispatcher");
}
