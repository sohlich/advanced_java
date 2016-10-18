/*
 */
package cz.fai.blog.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author František Špaček
 */
@Stateless
public class RunEveryTenSecondsBean {
    
    @Schedule(persistent = false, hour = "*", minute = "*", second = "*/10")
    public void print(){
        System.out.println("test");
    }
}
