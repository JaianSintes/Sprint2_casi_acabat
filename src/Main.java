import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //VARIABLES JOC MINES
    static int max = 30;
    static int[] taulell = new int[max];      // taulell
    static int jugador = 1;                  // informació del jugador
    static int j1 = 0, j2 = 0;                 // posició dels jugadors
    static int dau;                        // dau de 6 numeros
    static int pos;                        // per esbrinar aleatoris
    static int mines = 5;                    // quantitat maxima de mines
    static boolean fiJoc = false;            // indica quan acaba
    static Scanner scan = new Scanner(System.in);
    static Random rnd = new Random();


    //VARIABLES SPACE INVADERS

    static int maxF = 20, maxC = 20;
    static int[][] m = new int[maxF][maxC]; //taulell joc
    static int numDispars, col;
    static int posNau = maxC / 2;           //posició de la nau
    static boolean fijoc = false;
    static int vides = 5;                   //número de vides
    static String nau = "^";
    static boolean xoc = false;
    static int punts = 0;                   //puntuació
    static int velocitat = 500;             //velocitat de les estrelles


    //VARIABLES ATARI


    static int fila = 25; //0
    static int columna = 15; //0
    static int[][] matriujoc = new int[fila][columna];   //taulell
    static int posPilota = columna / 2;                 //posició de la pilota
    static String pilota = " * ";
    static String barra = "===";
    static int posbase = columna / 2;                     //posició de la base
    static boolean jocacaba = false;
    static String dolar = " $$";
    static String euro = " €€";
    static boolean piezasIN = false;
    static int posiciopece = 4;
    static int var = 0;
    static int puntuacio = 0;
    static int direcionpelota; //1= ARRIBA DERECHA 2= ARRIBA IZQUIERDA 3= ABAJO DERECHA 4= ABAJO IZQUIERDA


    //COLORS
    static String blauC = "\033[3;34m"; // per colorejar de blau
    static String roigN = "\033[1;31m"; // per colorejar de roig
    static String nc = "\033[0m"; // per retornar al color normal
    static String color = nc; // color per defecte de les estrelles
    static String colornau = nc;
    static String colorsEstreles = nc;


    public static void main(String[] args) throws InterruptedException {
        String opcio;
        String[] triarOpcio = {"minas", "space Invaders", "atari"};
        String FilePath; //Ruta dels demès jocs

        opcio = (String) JOptionPane.showInputDialog(
                null, "Escull el Joc", "Jocs", 3,
                null, triarOpcio, triarOpcio[1]);
        if (opcio.equals("space Invaders") || opcio.equals("atari")) {
            //<editor-fold desc="Codi dels botons">
            Frame f = new Frame("Taulell de control");
            f.setLayout(new FlowLayout());
            f.setSize(500, 100); // TAMAY DEL FRAME
            Label l = new Label();
            l.setText("Posa aquí el ratolí i prem botons dreta o esquera del teclat");
            f.add(l);
            f.setVisible(true);


            // POSEM EL CODI DEL LISTENER (cada cop que premem algun botó)
            KeyListener listener = new KeyListener() {
                @Override
                public void keyPressed(KeyEvent event) {
                    //atari
                    if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        // al prèmer tecla dreta
                        if (posbase < columna - 2) {
                            matriujoc[fila - 2][posbase] = 0;
                            posbase++;
                        }
                    }
                    if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        // al prèmer la tecla esquerra
                        if (posbase > 1) {                       // la idea és posar la nau a l'última fila i moure la columna (posNau)
                            matriujoc[fila - 2][posbase] = 0;            // <<-- Aqui tenies una fila més (maxF+1) i se'n passa de la matriu .
                            posbase--;                           // I la nau està a l'ultima fila (maxF-1)
                        }
                    }
                    //SPACEINVADERS
                    if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        // al prèmer tecla dreta
                        if (posNau < maxC - 1) {
                            m[maxF - 1][posNau] = 0;
                            posNau++;
                        }
                    }
                    if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        // al prèmer la tecla esquerra
                        if (posNau > 0) {                       // la idea és posar la nau a l'última fila i moure la columna (posNau)
                            m[maxF - 1][posNau] = 0;            // <<-- Aqui tenies una fila més (maxF+1) i se'n passa de la matriu .
                            posNau--;                           // I la nau està a l'ultima fila (maxF-1)
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    // printEventInfo("Key Released", event);
                }

                @Override
                public void keyTyped(KeyEvent event) {
                    // printEventInfo("Key Typed", event);
                }

                private void printEventInfo(String str, KeyEvent e) {
// System.out.println(str);
// int code = e.getKeyCode();
// System.out.println(" Code: " + KeyEvent.getKeyText(code));
// System.out.println(" Char: " + e.getKeyChar());
// int mods = e.getModifiersEx();
// System.out.println(" Mods: "
// + KeyEvent.getModifiersExText(mods));
// System.out.println(" Location: "
// + keyboardLocation(e.getKeyLocation()));
// System.out.println(" Action? " + e.isActionKey());
                }

                private String keyboardLocation(int keybrd) throws InterruptedException {
                    switch (keybrd) {
                        case KeyEvent.KEY_LOCATION_RIGHT:
                            return "Right";
                        case KeyEvent.KEY_LOCATION_LEFT:
                            return "Left";
                        case KeyEvent.KEY_LOCATION_NUMPAD:
                            return "NumPad";
                        case KeyEvent.KEY_LOCATION_STANDARD:
                            return "Standard";
                        case KeyEvent.KEY_LOCATION_UNKNOWN:
                        default:
                            return "Unknown";
                    }
                }
            };
            // AFEGIM EL listener AL JPANELL CREAT DALT
            f.addKeyListener(listener);
            //</editor-fold>
        }


        switch (opcio) {
            case "minas":
                //inicialitzar el vector a 0's
                for (int i = 0; i < max; i++) {
                    taulell[i] = 0;
                }

                // Posem 5 mines aleatories al vector
                do {
                    do {
                        pos = rnd.nextInt(max - 1) + 1;
                    } while (taulell[pos] == -1);
                    taulell[pos] = -1;
                    mines--;
                } while (mines > 0);

                // COMENÇA EL JOC
                do {
                    //Imprimir el taulell
                    for (int i = 0; i < max; i++) {
                        if (i == j1)
                            System.out.print(" 1 |");
                        else if (i == j2)
                            System.out.print(" 2 |");
                        if (taulell[i] == 0)
                            System.out.print("  |");
                        else if (taulell[i] == -1)
                            System.out.print(" *|");
                    }
                    System.out.println("\nTorn al jugador: " + jugador);
                    System.out.println("Tira el dau. Prem el 0 per tirar-lo");
                    dau = scan.nextInt();
                    dau = rnd.nextInt(6) + 1;  // número aleatori entre 1 i 6
                    System.out.println("Ha sortit el número: " + dau);

                    // moviment dels jugadors a partir del dau
                    // depent del jugador que juga actualment
                    if (jugador == 1) {
                        j1 = j1 + dau;
                        if (j1 >= max) {
                            fiJoc = true;
                            continue;
                        }
                        if (taulell[j1] == -1) {
                            Thread.sleep(1000);
                            System.err.println("JUGADOR " + j1 + " HAS XAFAT LA MINA!!");
                            j1 = 0;
                        }

                    } else {
                        j2 = j2 + dau;
                        if (j2 >= max) {
                            fiJoc = true;
                            continue;
                        }
                        if (taulell[j2] == -1) {
                            Thread.sleep(1000);
                            System.err.println("JUGADOR " + j2 + " HAS XAFAT LA MINA!!");
                            j2 = 0;
                        }
                    }

                    // canvi de jugador a cada tirada
                    if (jugador == 1)
                        jugador = 2;
                    else
                        jugador = 1;

                } while (!fiJoc);

                System.out.println("\n\n ENHORABONA JUGADOR: " + jugador);
                System.out.println("HAS GUANYAT!!");

                break;

            case "space Invaders":
//<editor-fold desc="Inicialitzem el tauell">
                // Inicializar
                for (int i = 0; i < maxF; i++) {
                    for (int j = 0; j < maxC; j++) {
                        m[i][j] = 0;
                    }
                }
                //</editor-fold>


                /** comencem el joc!!
                 * aquest do..while es repetirà tantes vegades com estem jugant
                 * posant la nau al lloc
                 * esquivant els xocs
                 * augmentant la velocitat
                 * etc...
                 */
                do {


                    //<editor-fold desc="Posició de la nau">
                    /* AQUEST CODI HA D'ANAR DINTRE DEL DO..WHILE DE TOT EL PROGRAMA */
                    // POSICICIÓ DE LA NAU.
                    // En una matriur m[i][j] --> i: representa la fila   j: representa la columna
                    // si tenim una matriu de 3x4:   maxF=3 i maxC=4  per tant la última fila és maxF-1 ->2 i la última col maxC-1 = 3
                    // les files son: i=0,  i=1,  i=2       --> ULTIMA FILA     = 2 (maxF-1)
                    // les cols son:  j=0,  j=1,  j=2, j=3  --> ÚLTIMA COLUMNA  = 3 (maxC-1)
                    m[maxF - 1][posNau] = 1;        // posem la nau en la última fila i la columna marcada amb la variable posNau
                    //</editor-fold>


                    //<editor-fold desc="Posem Estreles">
                    // Posem estreles
                    // numero aleatori de dispars entre 1 i 5
                    // columnes aleatories per cada dispar
                    // totes en la fila 0
                    numDispars = rnd.nextInt(5) + 1;

                    for (int i = 0; i < numDispars; i++) {
                        col = rnd.nextInt(maxC);
                        m[0][col] = -1;
                    }
                    //</editor-fold>


                    //<editor-fold desc="Baixem les estreles">
                    // baixar les estreles
                    for (int i = maxF - 1; i > 0; i--) {
                        for (int j = 0; j < maxC; j++) {
                            if (m[i - 1][j] == -1) {
                                m[i - 1][j] = 0;
                                m[i][j] = -1;
                            }
                        }
                    }
                    //</editor-fold>


                    //<editor-fold desc="Detectem el xoc">
                    // Detectar el choque
                    for (int j = 0; j < maxC; j++) {
                        if (m[maxF - 1][j] == -1 && posNau == j) {
                            xoc = true;           // posem el xoc a true cada cop que detectem un xoc
                            vides--;            // restem una vida
                            nau = "#(" + vides + ")";   // canviem l'aspecte de la nau a #(3)  indicant el número de vides que ens queden
                            colornau = roigN;   // canviem el color de la nau. L'utilitzarem quan imprimim la nau (en l'impresió del taulell)
                            break;              // com que hem detectat un xoc, ja hem de trencar el bucle i començar a imprimimr la matriu
                        } else {
                            colornau = nc;      // si no hi ha cap xoc la nau torna al color normal
                            nau = "^";          // i la nau torna al símbol normal
                        }
                    }
                    //</editor-fold>


                    //<editor-fold desc="Imprimim el taulell (matriu)">
                    // Imprimir
                    for (int i = 0; i < maxF; i++) {
                        for (int j = 0; j < maxC; j++) {
                            if (m[i][j] == 0)                                       // imprimim espais en blanc
                                System.out.print("   ");
                            else if (i == maxF - 1 && j == posNau) {                // imprimim la nau
                                System.out.print(colornau + " " + nau + " " + nc);
                                if (xoc) {
                                    Thread.sleep(1000);
                                    xoc = false;
                                }
                            } else if (m[i][j] == -1)                               // imprimim les estreles
                                System.out.print(colorsEstreles + " * " + nc);

                            if (i == maxF - 1 && m[i][j] == -1)                     // llevem les estreles de la fila maxF-1
                                m[i][j] = 0;                                        // sinó s'acumulen allà
                        }
                        System.out.println();                                       // per fer el salt de línia de final de fila
                    }
                    //</editor-fold>


                    //<editor-fold desc="Punts, velocitat i vides">
                    System.out.println("\n\n\n\n");
                    punts++;
                    if (punts >= 200)
                        colorsEstreles = blauC;

                    if (punts % 10 == 0 && velocitat > 100) {
                        velocitat = velocitat - 20;
                    }
                    Thread.sleep(velocitat);


                    if (vides <= 0)             // quan les vides siguen < o igual a 0 acabem el joc
                        fijoc = true;
                    //</editor-fold>

                } while (!fijoc);


                //<editor-fold desc="Informació del final del joc">
                System.out.println("\n\n HAS ACABAT EL JOC" + nc);
                System.out.println("\n\n\n\t\t\t TENS " + punts + " PUNTS");
                //</editor-fold>


                break;

            //<editor-fold desc="atari">
            case "atari":
                puntuacio = 0;
                direcionpelota = 1; //rnd.nextInt(2)+1;
                int negativespace = 0;
                boolean applynegativespace = false;
                //INICIALITZAR TAULELL
                for (int i = 0; i < fila; i++) {
                    for (int j = 0; j < columna; j++) {
                        if (i == 0 || i == fila - 1)
                            matriujoc[i][j] = 1;
                        else if (j == 0 || j == columna - 1)
                            matriujoc[i][j] = 1;
                        else if (i == fila - 3 && j == columna / 2)
                            matriujoc[i][j] = -1;
                        else if (i == fila - 2 && j == posbase)
                            matriujoc[i][j] = 3;
                        else if (i >= posiciopece && i <= posiciopece + 7) {

                            if (j >= negativespace && j <= columna - negativespace - 1) {
                                matriujoc[i][j] = 2;

                                applynegativespace = true; //inici de piràmide invertida ($$ i €€)
                            }


                        } else
                            matriujoc[i][j] = 0;

                    }


                    if (applynegativespace) {
                        negativespace++;
                    }


                }
                do {


//                    int negativespace = 0;
//                    boolean applynegativespace = false;
//                    //INICIALITZAR TAULELL
//                    for (int i = 0; i < fila; i++) {
//                        for (int j = 0; j < columna; j++) {
//                            if(i==0 || i==fila-1)
//                                matriujoc[i][j]=1;
//                            else if(j==0 || j==columna-1)
//                                matriujoc[i][j]=1;
//                            else if(i==fila-3 && j==columna/2)
//                                matriujoc[i][j]=-1;
//                            else if(i==fila-2 && j==posbase)
//                                matriujoc[i][j]=3;
//                            else if (i>=posiciopece && i<=posiciopece+7 ) {
//
//                                if (j>=negativespace && j<=columna-negativespace-1 ){
//                                    matriujoc[i][j]=2;
//
//                                    applynegativespace = true; //inici de piràmide invertida ($$ i €€)
//                                }
//
//
//
//
//
//                            } else
//                                matriujoc[i][j]=0;
//
//                        }
//
//
//                        if (applynegativespace){
//                            negativespace++;
//                        }
//
//
//                    }


                    //IMPRIMIR TAULELL
                    System.out.println("\t\t\tA R K A N O I D \t\t" + puntuacio);
                    System.out.println("\t\t\t- - - - - - - - \t\t");
                    for (int i = 0; i < fila; i++) {
                        for (int j = 0; j < columna; j++) {
                            if (matriujoc[i][j] == 1)
                                System.out.print(" # ");  //parets taulell
                            else if (matriujoc[i][j] == -1) {
                                System.out.print(pilota);
                                //System.out.println("i="+i+" j="+j);//col·locar pilota
                            } else if (i == fila - 2 && j == posbase) {
                                matriujoc[i][j] = 3;
                                System.out.print(barra);  //barra

                            } else if (matriujoc[i][j] == 0)
                                System.out.print("   ");  //imprimir espais en blanc
                            else if (matriujoc[i][j] == 2) {

                                if (i % 2 != 1) {
                                    System.out.print(euro);  //peçes joc

                                } else
                                    System.out.print(dolar);  //peçes joc

                            }
                        }
                        System.out.println();
                        //IMPRIMIR FILA PER FILA

                    }
                    System.out.println();
                    System.out.println();
// ERROR EN EL CASO 2 O 4
                    switch (direcionpelota) {

                        case 1:
                            for (int i = 0; i < fila; i++) {
                                for (int j = columna - 1; j >= 0; j--) {

                                    if (matriujoc[i][j] == -1) {

                                        if (matriujoc[i][j + 1] == 1 || matriujoc[i - 1][j] == 1) {

                                            direcionpelota = 2;





                                            i = fila;
                                            j=-1;
                                        } else if (matriujoc[i - 1][j + 1] == 2) {

                                            direcionpelota = 3;


                                            matriujoc[i][j] = 0;
//                                            matriujoc[i + 1][j + 1] = -1;
                                            matriujoc[i - 1][j + 1] = -1;
                                            puntuacio += 10;
                                            i = fila;
                                            j=-1;
                                        } else {

                                            matriujoc[i - 1][j + 1] = -1;
                                            matriujoc[i][j] = 0;
                                            i = fila;
                                            j=-1;
                                        }

                                    }

                                }

                            }
                            break;
                        case 2:
                            for (int i = 0; i < fila; i++) {
                                for (int j = 1; j <= columna - 2; j++) {

                                    if (matriujoc[i][j] == -1) {


                                        if (matriujoc[i - 1][j - 1] == 1) {

                                            direcionpelota = 1;



                                            i = fila;
                                            j=columna;

                                        } else if (matriujoc[i - 1][j - 1] == 2) {  //li he posat + i + quan havia un - i - i ha funcionat lode revotar en la paret quan es quedava estancada
                                            direcionpelota = 4;
                                            matriujoc[i - 1][j - 1] = -1;
                                            matriujoc[i][j] = 0;
//                                           matriujoc[i-1][j-1]=0;
                                            puntuacio += 10;
                                            i = fila;
                                            j=columna;
                                        } else {

                                            matriujoc[i - 1][j - 1] = -1;
                                            matriujoc[i][j] = 0;
                                            i = fila;
                                            j=columna;
                                        }

                                    }

                                }

                            }
                            break;
                        case 3:
                            for (int i = fila - 1; i >= 0; i--) {
                                for (int j = columna - 2; j >= 1; j--) {

                                    if (matriujoc[i][j] == -1) {

                                        if (matriujoc[i + 1][j + 1] == 1 || matriujoc[i-1][j+1] == 1) {

                                            direcionpelota = 4;

                                            matriujoc[i + 1][j - 1] = -1;
                                            matriujoc[i][j] = 0;


                                            i = -1;
                                            j = -1;
                                        }else if (matriujoc[i + 1][j] == 3||matriujoc[i + 1][j+1] == 3) {
                                            direcionpelota = 1;

                                            matriujoc[i - 1][j] = -1;
                                            matriujoc[i][j] = 0;
                                                i = fila;
                                                j=-1;
                                        } else if (matriujoc[i + 1][j + 1] == 2) {
                                            direcionpelota = 1;
//                                           matriujoc[i - 1][j + 1] = -1;
//                                           matriujoc[i][j] = 0;
//                                           matriujoc[i - 1][j + 1] = -1;
                                            puntuacio += 10;
                                            i = -1;
                                            j=-1;
                                        } else {

                                            matriujoc[i + 1][j + 1] = -1;
                                            matriujoc[i][j] = 0;
                                            i = -1;
                                            j=-1;
                                        }

                                    }

                                }

                            }
                            break;
                        case 4:
                            for (int i = 0; i < fila - 1; i++) {
                                for (int j = columna - 1; j >= 0; j--) {

                                    if (matriujoc[i][j] == -1) {


                                        if (matriujoc[i][j + 1] == 1 || matriujoc[i + 1][j - 1] == 2 || matriujoc[i - 1][j] == 1 || matriujoc[i][j-1] == 1) {

                                            direcionpelota = 3;





                                            i = fila;
                                            j=-1;
                                        } else if (matriujoc[i + 1][j] == 3) {
                                            direcionpelota = 2;
                                            i = fila;
                                            j=-1;
                                        } else if (matriujoc[i + 1][j-1] == 2) {
                                            direcionpelota = 2;
//                                           matriujoc[i - 1][j - 1] = -1;
                                            matriujoc[i][j] = 0;
                                            matriujoc[i + 1][j - 1] = -1;
                                            puntuacio += 10;
                                            i = fila;
                                            j=-1;
                                        } else {

                                            matriujoc[i + 1][j - 1] = -1;
                                            matriujoc[i][j] = 0;
                                            i = fila;
                                            j=-1;


                                        }

                                    }

                                }

                            }
                            break;


                    }




                    Thread.sleep(300);


                } while (!jocacaba);

                break;
            //</editor-fold>

            default:
                JOptionPane.showMessageDialog(null, "no has escollit res maquina", "bobo", 0);
                break;

        }
        System.out.println("Hello world!");
    }
}