package framework.membership;

import framework.membership.Proxy.IProxyFacade;
import framework.membership.Proxy.ProxyFacadeImp;
import framework.membership.Proxy.Validate.StringType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Duong Truong on 1/31/2018.
 */
public abstract class Member {
    private String id;
    private Map<String,String> metadata;
    private IProxyFacade proxy;

    public Member(){
        id = generateId();
        metadata = new HashMap<>();
        proxy = ProxyFacadeImp.getInstance();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IProxyFacade getProxy() {
        return proxy;
    }

    public void setProxy(IProxyFacade proxy) {
        this.proxy = proxy;
    }

    public abstract String generateId();

    public boolean validate(StringType type, String input){
        return proxy.validateString(type,input);
    }

    public void saveOneData(String key, String value){
        metadata.put(key,value);
    }

    public String getOneData(String key){
        return metadata.get(key);
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
