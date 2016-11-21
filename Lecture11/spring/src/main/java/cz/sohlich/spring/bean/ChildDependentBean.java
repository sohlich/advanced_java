package cz.sohlich.spring.bean;

/**
 * Created by Radomir Sohlich on 11/19/16.
 */
public class ChildDependentBean {

    private final SampleBean injectedBean;

    public ChildDependentBean(SampleBean injectedBean) {
        this.injectedBean = injectedBean;
    }


    public String getInfo() {
        return injectedBean.getTime() + "";
    }
}
