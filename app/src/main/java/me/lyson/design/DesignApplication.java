package me.lyson.design;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lyson on 15/3/27.
 */
public class DesignApplication extends Application{
    private static DesignApplication instance;
    private DesignApplication(){}
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * !不要在Activity或View里调用此方法!
     * <a href="http://stackoverflow.com/questions/987072/using-application-context-everywhere">参考说明</a>
     * @return
     */
    public static Context getContext() {
        if (instance==null){
            instance=new DesignApplication();
        }
        return instance;
    }
}
