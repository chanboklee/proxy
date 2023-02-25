package hello.proxy.pureproxy.concreteproxy.code;

public class ConCreteClient {

    private ConcreteLogic concreteLogic;

    public ConCreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute(){
        concreteLogic.operation();
    }
}
