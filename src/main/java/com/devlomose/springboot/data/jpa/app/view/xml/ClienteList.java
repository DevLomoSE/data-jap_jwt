package com.devlomose.springboot.data.jpa.app.view.xml;

import com.devlomose.springboot.data.jpa.app.models.entity.Cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * ClienteList at: src/main/java/com/devlomose/springboot/data/jpa/app/view/xml
 * Created by @DevLomoSE at 29/9/21 13:45.
 */
@XmlRootElement(name="clientes")
public class ClienteList {

    @XmlElement(name="cliente")
    public List<Cliente> clientes;

    public ClienteList() {
    }

    public ClienteList(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
