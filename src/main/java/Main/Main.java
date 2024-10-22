package Main;

import Utillity.SQLFileRunner;

public class Main {
    public static void main(String[] args) {
        SQLFileRunner sqlFileRunner = new SQLFileRunner();
        sqlFileRunner.runSQLFile();

        Terminal terminal = new Terminal();
        terminal.runTerminal();




    }

}
