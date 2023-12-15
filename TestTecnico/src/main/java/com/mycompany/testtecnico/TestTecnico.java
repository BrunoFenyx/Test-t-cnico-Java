/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testtecnico;

import com.mycompany.testtecnico.igu.StartPanel;
import com.mycompany.testtecnico.persistence.PersistenceController;

/**
 *
 * @author WIN10
 */
public class TestTecnico {

    public static void main(String[] args) {
        StartPanel screen = new StartPanel();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);
        
        
        PersistenceController persisController = new PersistenceController();
    }
}
