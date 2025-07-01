/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainTest.java;
import LoginForm.java.LoginForm;
import config.config;
import java.sql.Connection;

/**
 *
 * @author Kharisma.S
 */
public class MainTest {
    public static void main(String[] args) {
        new LoginForm().setVisible(true); 
        Connection conn = config.getConnection();
    }
}