package service.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import service.listener.task.StatusListener;


public class OnlineStatusListener implements ServletContextListener {
    
    private static Timer timer = null;
    private static StatusListener task = null;
    public void contextDestroyed(ServletContextEvent event) {
        //stop
        if(timer != null)
            timer.cancel();
    }

    public void contextInitialized(ServletContextEvent event) {
    	task = new StatusListener(event);
        timer = new Timer(true);
        
        //start
        //GregorianCalendar now = new GregorianCalendar();
        //now.set(Calendar.HOUR, 0);
        //now.set(Calendar.MINUTE, 0);
        //now.set(Calendar.SECOND, 0);
        //timer.schedule(task, now.getTime());
        System.out.println("啟動後每1分鐘自動重新載入線上人數");
        timer.schedule(task, 0, 60000);//每1000*60*15分鐘？
    }

}