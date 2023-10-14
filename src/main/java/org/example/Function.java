package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Function {
    int totalStates = 141 + 2;
    FileManager fm;
    NodeObject []states;
    nodeInformation currentState;
    nodeInformation q0 = new nodeInformation('\0',0);
    Boolean moved;
    int stateNum,
            x,y,aceptedCount;
    HashMap<Integer,String> keywords;

    Function(){
        createNodes();
        setFinalNodes();
        createKeywords();
        fm = new FileManager();
        fm.createFile();
        currentState = q0;
        stateNum = x = y = aceptedCount = 1;
    }

    public void recorrido(char c){
        if(c == '\0'){ y++; return; }
        x++;
        moved = false;

        this.fm.appendMoviment( "Q" + currentState.state);

        ArrayList<nodeInformation> informationActual = states[stateNum].getInfo();
        for (nodeInformation nodeInformation : informationActual) {
            if(nodeInformation.character == c){
                moved = true;
                currentState = nodeInformation;
            }
        }
        if(!moved){
            currentState = q0;
            informationActual = states[0].getInfo();
            for (nodeInformation nodeInformation : informationActual) {
                if(nodeInformation.character == c){
                    moved = true;
                    currentState = nodeInformation;
                }
            }
        }
        stateNum = currentState.state;
        //System.out.println("Q" + currentState.state + ". " + c);
        this.fm.appendMoviment( " -- " + c +" --> Q" + currentState.state + "\n");
        if(states[stateNum].isFinalNode){
            //System.out.println("Final node!");
            //System.out.println( aceptedCount+ ". Word formed: ["+keywords.get(stateNum)+"]" + " (" + (x-keywords.get(stateNum).length()) + "," + y + ")");
            this.fm.appendAccepted(aceptedCount+ ". Word formed ["+keywords.get(stateNum)+"]" + " at (" + (x-keywords.get(stateNum).length()) + "," + y + ")\n");
            aceptedCount++;
        }
    }


    //Despreciar función, es larguisima
    void createNodes(){
        this.states = new NodeObject[totalStates];
        //se inicializan los estados
        for(int i = 0; i < totalStates ; i++)
            this.states[i] = new NodeObject();

        //primer capa, solo interactuan entre si, o con sus subniveles del nivel 3
        this.states[0].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('a',1),
                new nodeInformation('b',2),
                new nodeInformation('c',3),
                new nodeInformation('d',4),
                new nodeInformation('e',5),
                new nodeInformation('f',6),
                new nodeInformation('g',7),
                new nodeInformation('l',8),
                new nodeInformation('r',9),
                new nodeInformation('s',10),
                new nodeInformation('t',11),
                new nodeInformation('u',12),
                new nodeInformation('v',13),
                new nodeInformation('w',14),
                new nodeInformation('i',15),
        });
        //primer capa
        this.states[1].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('a',1),
                new nodeInformation('u',16),
        });
        this.states[2].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('b',2),
                new nodeInformation('r',17),
        });
        this.states[3].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('c',3),
                new nodeInformation('a',18),
                new nodeInformation('h',19),
                new nodeInformation('o',20),
        });
        this.states[4].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('d',4),
                new nodeInformation('e',21),
                new nodeInformation('o',22),
        });
        this.states[5].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[6].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('f',6),
                new nodeInformation('l',26),
                new nodeInformation('o',27),
        });
        this.states[7].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('g',7),
                new nodeInformation('o',28),
        });
        this.states[8].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[9].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[10].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('s',10),
                new nodeInformation('h',31),
                new nodeInformation('i',32),
                new nodeInformation('t',33),
                new nodeInformation('w',34),
        });
        this.states[11].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[12].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('u',12),
                new nodeInformation('n',36),
        });
        this.states[13].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('v',13),
                new nodeInformation('o',37),
        });
        this.states[14].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('w',14),
                new nodeInformation('h',38),
        });
        this.states[15].addSeveralInfo(new nodeInformation[]{
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        //Segunda capa
        this.states[16].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',16),
                new nodeInformation('t', 41),
                new nodeInformation('n', 36),
                new nodeInformation('y', 36),
        });
        this.states[17].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',17),
                new nodeInformation('e', 42),
        });
        this.states[18].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',18),
                new nodeInformation('s', 43),
                new nodeInformation('u', 16),
        });
        this.states[19].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('h',19),
                new nodeInformation('a', 44),
        });
        this.states[20].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',20),
                new nodeInformation('n', 45),
        });
        this.states[21].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',21),
                new nodeInformation('f', 46),
                new nodeInformation('l', 23),
                new nodeInformation('n', 24),
                new nodeInformation('x', 25),
        });
        this.states[22].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',22),
                new nodeInformation('u', 47),
        });
        this.states[23].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',23),
                new nodeInformation('s', 48),
                new nodeInformation('o', 29),
        });
        this.states[24].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',24),
                new nodeInformation('u', 49),
        });
        this.states[25].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('x',25),
                new nodeInformation('t', 50),
        });
        this.states[26].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',26),
                new nodeInformation('o', 51),
        });
        this.states[27].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',27),
                new nodeInformation('r', 52),
        });
        this.states[28].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',28),
                new nodeInformation('t', 53),
        });
        this.states[29].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',29),
                new nodeInformation('n', 54),
        });
        this.states[30].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',30),
                new nodeInformation('g', 55),
                new nodeInformation('t', 56),
                new nodeInformation('l', 23),
                new nodeInformation('n', 24),
                new nodeInformation('x', 25),
        });
        this.states[31].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('h',31),
                new nodeInformation('o', 57),
        });
        this.states[32].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',32),
                new nodeInformation('g', 58),
                new nodeInformation('z', 59),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[33].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',33),
                new nodeInformation('a', 60),
                new nodeInformation('r', 61),
                new nodeInformation('y',35),
        });
        this.states[34].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('w',34),
                new nodeInformation('i', 62),
                new nodeInformation('h', 38),
        });
        this.states[35].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('y',35),
                new nodeInformation('p', 63),
        });
        this.states[36].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',36),
                new nodeInformation('i', 64),
                new nodeInformation('s', 65),
        });
        this.states[37].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',37),
                new nodeInformation('i', 66),
                new nodeInformation('l', 67),
        });
        this.states[38].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('h',38),
                new nodeInformation('i', 68),
        });
        this.states[39].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('f',39),
                //new nodeInformation(' ', ),
                new nodeInformation('f',6),
                new nodeInformation('l',26),
                new nodeInformation('o',27),
        });
        this.states[40].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',40),
                new nodeInformation('t', 69),
        });
        this.states[41].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',41),
                new nodeInformation('o', 70),
                new nodeInformation('y', 31),
        });
        this.states[42].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',42),
                new nodeInformation('a', 71),
                new nodeInformation('g', 55),
                new nodeInformation('t', 56),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[43].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('s',43),
                new nodeInformation('e', 72),
                new nodeInformation('s',10),
                new nodeInformation('h',31),
                new nodeInformation('i',32),
                new nodeInformation('t',33),
                new nodeInformation('w',34),
        });
        this.states[44].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',44),
                new nodeInformation('r', 73),
                new nodeInformation('u', 16),
        });
        this.states[45].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',45),
                new nodeInformation('s', 74),
                new nodeInformation('t', 75),
        });
        this.states[46].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('f',46),
                new nodeInformation('a', 76),
                new nodeInformation('f',6),
                new nodeInformation('l',26),
                new nodeInformation('o',27),
        });
        this.states[47].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',47),
                new nodeInformation('b', 77),
                new nodeInformation('u',12),
                new nodeInformation('n',36),

        });
        this.states[48].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('s',48),
                new nodeInformation('e', 78),
                new nodeInformation('s',10),
                new nodeInformation('h',31),
                new nodeInformation('i',32),
                new nodeInformation('t',33),
                new nodeInformation('w',34),
        });
        this.states[49].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',49),
                new nodeInformation('m', 79),
                new nodeInformation('u',12),
                new nodeInformation('n',36),
        });
        this.states[50].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',50),
                new nodeInformation('e', 80),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[51].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',51),
                new nodeInformation('a', 81),
                new nodeInformation('n', 54),
        });
        this.states[52].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',52),
                //new nodeInformation(' ', ),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[53].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',53),
                new nodeInformation('o', 82),
                new nodeInformation('t',11),
                new nodeInformation('y',35),

        });
        this.states[54].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',54),
                new nodeInformation('g',83),
        });
        this.states[55].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('g',55),
                new nodeInformation('i', 84),
                new nodeInformation('g',7),
                new nodeInformation('o',28),
        });
        this.states[56].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',56),
                new nodeInformation('t',11),
                new nodeInformation('u',85),
                new nodeInformation('y',35),
        });
        this.states[57].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',57),
                new nodeInformation('r', 86),
        });
        this.states[58].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('g',58),
                new nodeInformation('n', 87),
                new nodeInformation('g',7),
                new nodeInformation('o',28),
        });
        this.states[59].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('z',59),
                new nodeInformation('e', 88),
        });
        this.states[60].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',60),
                new nodeInformation('t', 89),
                new nodeInformation('a',1),
                new nodeInformation('u',16),
        });
        this.states[61].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',61),
                new nodeInformation('u', 90),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[62].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',62),
                new nodeInformation('t', 91),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[63].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('p',63),
                new nodeInformation('e', 92),
        });
        this.states[64].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',64),
                new nodeInformation('o', 93),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[65].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('s',65),
                new nodeInformation('i', 94),
        });
        this.states[66].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',66),
                new nodeInformation('d', 95),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),

        });
        this.states[67].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',67),
                new nodeInformation('a', 96),
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[68].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',68),
                new nodeInformation('l', 97),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[69].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',69),
                //new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[70].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',70),
                //new nodeInformation(' ', ),
        });
        this.states[71].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',71),
                new nodeInformation('k', 98),
                new nodeInformation('a',1),
                new nodeInformation('u',16),
        });
        this.states[72].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',72),
                //new nodeInformation(' ', ),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[73].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',73),
                //new nodeInformation(' ', ),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[74].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('s',74),
                new nodeInformation('t', 99),
                new nodeInformation('s',10),
                new nodeInformation('h',31),
                new nodeInformation('i',32),
                new nodeInformation('w',34),
        });
        this.states[75].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',75),
                new nodeInformation('i', 100),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[76].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',76),
                new nodeInformation('u', 101),
                new nodeInformation('a',1),
        });
        this.states[77].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('b',77),
                new nodeInformation('l', 102),
                new nodeInformation('b',2),
                new nodeInformation('r',17),
        });
        this.states[78].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',78),
//                new nodeInformation(' ', ),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[79].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('m',79),
//                new nodeInformation(' ', ),
        });
        this.states[80].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',80),
                new nodeInformation('r', 103),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[81].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',81),
                new nodeInformation('t', 104),
                new nodeInformation('a',1),
                new nodeInformation('u',16),
        });
        this.states[82].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',82),
//                new nodeInformation(' ', ),
        });
        this.states[83].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('g',83),
//                new nodeInformation(' ', ),
                new nodeInformation('g',7),
                new nodeInformation('o',28),
        });
        this.states[84].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',84),
                new nodeInformation('s', 105),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[85].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',85),
                new nodeInformation('r', 106),
                new nodeInformation('u',12),
                new nodeInformation('n',36),
        });
        this.states[86].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',86),
                new nodeInformation('t', 107),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[87].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',87),
                new nodeInformation('e', 108),
        });
        this.states[88].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',88),
                new nodeInformation('o', 109),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[89].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',89),
                new nodeInformation('i', 110),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[90].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',90),
                new nodeInformation('c', 111),
                new nodeInformation('u',12),
                new nodeInformation('n',36),
        });
        this.states[91].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',91),
                new nodeInformation('c', 112),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[92].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',92),
                new nodeInformation('d', 113),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[93].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',93),
                new nodeInformation('n', 114),
        });
        this.states[94].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',94),
                new nodeInformation('g', 115),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),

        });
        this.states[95].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('d',95),
//                new nodeInformation(' ', ),
                new nodeInformation('d',4),
                new nodeInformation('e',21),
                new nodeInformation('o',22),

        });
        this.states[96].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('a',96),
                new nodeInformation('t', 116),
                new nodeInformation('a',1),
                new nodeInformation('u',16),
        });
        this.states[97].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',97),
                new nodeInformation('e', 117),
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[98].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('k',98),
//                new nodeInformation(' ', ),

        });
        this.states[99].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',99),
//                new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
                new nodeInformation('a',60),
                new nodeInformation('r',61),
        });
        this.states[100].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',100),
                new nodeInformation('n', 118),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
        });
        this.states[101].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',101),
                new nodeInformation('l', 119),
                new nodeInformation('u',12),
                new nodeInformation('n',36),
                new nodeInformation('t',41),
        });
        this.states[102].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',102),
                new nodeInformation('e', 120),
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[103].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',103),
                new nodeInformation('n', 121),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[104].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',104),
//                new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[105].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('s',105),
                new nodeInformation('e', 122),
                new nodeInformation('s',10),
                new nodeInformation('h',31),
                new nodeInformation('i',32),
                new nodeInformation('w',34),
                new nodeInformation('t',141),
        });
        this.states[106].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',106),
                new nodeInformation('n', 123),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[107].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',107),
//                new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[108].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',108),
                new nodeInformation('d', 124),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[109].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('o',109),
                new nodeInformation('f', 125),
        });
        this.states[110].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',110),
                new nodeInformation('c', 126),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[111].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('c',111),
                new nodeInformation('t', 127),
                new nodeInformation('c',3),
                new nodeInformation('a',18),
                new nodeInformation('h',19),
                new nodeInformation('o',20),
        });
        this.states[112].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('c',112),
                new nodeInformation('h', 128),
                new nodeInformation('c',3),
                new nodeInformation('a',18),
                new nodeInformation('o',20),
        });
        this.states[113].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('d',113),
                new nodeInformation('e', 129),
                new nodeInformation('d',4),
                new nodeInformation('o',22),
        });
        this.states[114].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',114),
//                new nodeInformation(' ', ),

        });
        this.states[115].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('g',115),
                new nodeInformation('n', 130),
                new nodeInformation('g',7),
                new nodeInformation('o',28),
        });
        this.states[116].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',116),
                new nodeInformation('i', 131),
                new nodeInformation('t',11),
                new nodeInformation('y',35),

        });
        this.states[117].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',117),
//                new nodeInformation(' ', ),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[118].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',118),
                new nodeInformation('u', 132),
                new nodeInformation('t', 69),
        });
        this.states[119].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',119),
                new nodeInformation('t', 133),
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[120].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',120),
//                new nodeInformation(' ', ),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[121].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',121),
//                new nodeInformation(' ', ),
        });
        this.states[122].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',122),
                new nodeInformation('r', 134),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[123].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',123),
//                new nodeInformation(' ', ),
        });
        this.states[124].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('d',124),
//                new nodeInformation(' ', ),
                new nodeInformation('d',4),
                new nodeInformation('e',21),
                new nodeInformation('o',22),
        });
        this.states[125].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('f',125),
//                new nodeInformation(' ', ),
                new nodeInformation('f',6),
                new nodeInformation('l',26),
                new nodeInformation('o',27),
        });
        this.states[126].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('c',126),
//                new nodeInformation(' ', ),
                new nodeInformation('c',3),
                new nodeInformation('a',18),
                new nodeInformation('h',19),
                new nodeInformation('o',20),
        });
        this.states[127].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',127),
//                new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),
        });
        this.states[128].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('h',128),
//                new nodeInformation(' ', ),
                new nodeInformation('a', 44),
        });
        this.states[129].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',129),
                new nodeInformation('f', 135),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[130].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('n',130),
                new nodeInformation('e', 136),
        });
        this.states[131].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('i',131),
                new nodeInformation('l', 137),
                new nodeInformation('i',15),
                new nodeInformation('f',39),
                new nodeInformation('n',40),
        });
        this.states[132].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('u',132),
                new nodeInformation('e', 138),
                new nodeInformation('u',12),
                new nodeInformation('n',36),
        });
        this.states[133].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',133),
//                new nodeInformation(' ', ),
                new nodeInformation('t',11),
                new nodeInformation('y',35),

        });
        this.states[134].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('r',134),
//                new nodeInformation(' ', ),
                new nodeInformation('r',9),
                new nodeInformation('e',30),
        });
        this.states[135].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('f',135),
//                new nodeInformation(' ', ),
                new nodeInformation('f',6),
                new nodeInformation('l',26),
                new nodeInformation('o',27),
                new nodeInformation('a',76),
        });
        this.states[136].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',136),
                new nodeInformation('d', 139),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[137].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('l',137),
                new nodeInformation('e', 140),
                new nodeInformation('l',8),
                new nodeInformation('o',29),
        });
        this.states[138].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',138),
//                new nodeInformation(' ', ),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),
        });
        this.states[139].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('d',139),
                new nodeInformation('d',4),
                new nodeInformation('e',21),
                new nodeInformation('o',22),
        });
        this.states[140].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('e',140),
                new nodeInformation('e',5),
                new nodeInformation('l',23),
                new nodeInformation('n',24),
                new nodeInformation('x',25),

        });
        this.states[141].addSeveralInfo(new nodeInformation[]{
                //new nodeInformation('t',141),
                new nodeInformation('e',122),
                new nodeInformation('t',11),
                new nodeInformation('a',60),
                new nodeInformation('r',61),
        });

        //Ending states: {22,39,52,69,70,72,73,78,79,82,83,95,98,99,104,107,
        // 114,117,120,121,123,124,125,126,127,128,133,134,138,139,140,141}

    }
    void setFinalNodes() {
        states[22].setFinalNode();
        states[39].setFinalNode();
        states[52].setFinalNode();
        states[69].setFinalNode();
        states[70].setFinalNode();
        states[72].setFinalNode();
        states[73].setFinalNode();
        states[78].setFinalNode();
        states[79].setFinalNode();
        states[82].setFinalNode();
        states[83].setFinalNode();
        states[95].setFinalNode();
        states[98].setFinalNode();
        states[99].setFinalNode();
        states[104].setFinalNode();
        states[107].setFinalNode();
        states[114].setFinalNode();
        states[117].setFinalNode();
        states[120].setFinalNode();
        states[121].setFinalNode();
        states[123].setFinalNode();
        states[124].setFinalNode();
        states[125].setFinalNode();
        states[126].setFinalNode();
        states[127].setFinalNode();
        states[128].setFinalNode();
        states[133].setFinalNode();
        states[134].setFinalNode();
        states[135].setFinalNode();
        states[138].setFinalNode();
        states[139].setFinalNode();
        states[140].setFinalNode();
    }
    void createKeywords(){
        keywords = new HashMap<>();
        keywords.put(70 ,"auto");
        keywords.put(98 ,"break");
        keywords.put(72 ,"case");
        keywords.put(73 ,"char");
        keywords.put(99 ,"const");
        keywords.put(138 ,"continue");
        keywords.put(133 ,"default");
        keywords.put(22 ,"do");
        keywords.put(120 ,"double");
        keywords.put(78 ,"else");
        keywords.put(79 ,"enum");
        keywords.put(121 ,"extern");
        keywords.put(104 ,"float");
        keywords.put(52 ,"for");
        keywords.put(82 ,"goto");
        keywords.put(39 ,"if");
        keywords.put(69 ,"int");
        keywords.put(83 ,"long");
        keywords.put(134 ,"register");
        keywords.put(123 ,"return");
        keywords.put(107 ,"short");
        keywords.put(124 ,"signed");
        keywords.put(125 ,"sizeof");
        keywords.put(126 ,"static");
        keywords.put(127 ,"struct");
        keywords.put(128 ,"switch");
        keywords.put(135 ,"typedef");
        keywords.put(114 ,"union");
        keywords.put(139 ,"unsigned");
        keywords.put(95 ,"void");
        keywords.put(140 ,"volatile");
        keywords.put(117 ,"while");
    }
}

class nodeInformation{
    int state;
    char character;

    nodeInformation(char character, int state){
        this.character = character;
        this.state = state;
    }

}


class NodeObject{
    ArrayList<nodeInformation> information;
    boolean isFinalNode = false;

    NodeObject(){
        information = new ArrayList<>();
    }

    void addSeveralInfo(nodeInformation[] infoData){
        information.addAll(Arrays.asList(infoData));
    }

    ArrayList<nodeInformation> getInfo(){
        return information;
    }

    void setFinalNode(){
        this.isFinalNode = true;
    }
}