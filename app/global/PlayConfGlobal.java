package global;

import play.GlobalSettings;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Results;
import play.mvc.SimpleResult;
import play.mvc.Http.RequestHeader;

public class PlayConfGlobal extends GlobalSettings {

    @Override
    public Promise<SimpleResult> onHandlerNotFound(RequestHeader arg0) {
       return F.Promise.<SimpleResult>pure(Results.notFound(views.html.error.render()));
    }
    
    @Override
    public Promise<SimpleResult> onError(RequestHeader arg0, Throwable arg1) {
        return F.Promise.<SimpleResult>pure(Results.internalServerError(views.html.error.render()));
    }
}
