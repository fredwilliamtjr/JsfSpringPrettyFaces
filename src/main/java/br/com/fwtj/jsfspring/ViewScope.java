package br.com.fwtj.jsfspring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import java.util.Map;


//ESSA CLASSE IMPLEMENTA O O VIEW SCOPE NO SPRING
public class ViewScope implements Scope {

    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (FacesContext.getCurrentInstance().getViewRoot() != null) {
            Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap(); //Map do JSF cujo os valores sÃ£o referentes ao escopo de view
            if (viewMap.containsKey(name)) {
                return viewMap.get(name);
            } else {
                Object object = objectFactory.getObject();
                viewMap.put(name, object); //da prÃ³xima vez que for requisitado, dentro do escopo de view, ele nÃ£o precisarÃ¡ ser construÃ­do.
                return object;
            }
        } else {
            return null;
        }
    }

    public Object remove(String name) {
        if (FacesContext.getCurrentInstance().getViewRoot() != null) {
            return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name); //simplesmente, remove o bean Spring do ViewMap JSF
        } else {
            return null;
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {}

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}