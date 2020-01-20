package service.listener.task;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.impl.DataFinder;

public class StatusListener extends TimerTask {
    private ServletContextEvent event = null;
    
    
    public StatusListener(ServletContextEvent event){
        this.event = event;
    }
    
    public void run(){    	
    	
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:../applicationContext.xml");
    	DataFinder dm =(DataFinder)context.getBean("DataFinder");
        context.registerShutdownHook();		
		ServletContext servletContext = event.getServletContext();
    	
    	
		//System.out.println("線上使用者(Map)SYS_ONLINE_STATUS");
		//System.out.println("-------------------------------");
		//System.out.println(dm.sqlGetMap("SELECT * FROM SYS_ONLINE_STATUS ORDER BY Oid DESC"));
		servletContext.setAttribute("SYS_ONLINE_STATUS", dm.sqlGetMap("SELECT * FROM SYS_ONLINE_STATUS ORDER BY Oid DESC"));
		context.close();
    }

}