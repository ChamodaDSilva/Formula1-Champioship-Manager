import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//this class for search a driver participated races with the position of his/ with swing gui
public class SearchRaceGUI {

    SearchRaceGUI(){
        JTable table =new JTable();//table
        Object [] columns ={"Race Date","Position"};//columns
        DefaultTableModel model=new DefaultTableModel();

        JFrame frame= new JFrame("Search Driver");//set the title of the frame when making the object
        frame.getContentPane().setBackground(new Color(21,41,71));//frame background color
        frame.getContentPane().setForeground(Color.WHITE);//word colors in the frame
        frame.setBounds(100,100,1000,500);//bounds of the table

        frame.getContentPane().setLayout(null);//no layouts
        frame.setLocationRelativeTo(null);//no relatives

        try {//changed the app icon
            frame.setIconImage(ImageIO.read(new File("src/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.setColumnIdentifiers(columns);//added columns for the model
        table.getTableHeader().setBackground(new Color(21,41,71));//column header color
        table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,19));//column header
        table.getTableHeader().setForeground(Color.WHITE);//table column name colors
        table.setModel(model);//set model for the table

        table.setBackground(new Color(139, 144, 196));//table background color setting
        table.setForeground(Color.black);//table word color setting
        table.setSelectionBackground(Color.BLUE);//setting the selection row background color
        table.setGridColor(new Color(21,41,71));//setting grid color
        table.setSelectionForeground(Color.white);//table selection wod color
        table.setFont(new Font("Tahoma",Font.PLAIN,17));
        table.setRowHeight(30);//table row height
        table.setAutoCreateRowSorter(true);//auto sorter activated

        JScrollPane pane= new JScrollPane(table);//pane
        pane.setForeground(Color.BLUE);//pane word color
        pane.setBackground(Color.WHITE);//pa
        pane.setBounds(0,60,1000,550);
        frame.getContentPane().add(pane);
        Formula1ChampionshipManager data=new Formula1ChampionshipManager();

        //waring if driver not in the list
        JLabel warning=new JLabel();//warning label
        warning.setBounds(310,10,250,40);
        warning.setForeground(Color.white);
        frame.getContentPane().add(warning);

        ArrayList<Race> participatedRaces= new ArrayList<>();// to store races participated by the driver
        //textField
        JTextField tf=new JTextField();//inputting field for search
        tf.setBounds(140, 10, 150, 35);
        frame.getContentPane().add(tf);

        //icon of the button
        ImageIcon searchIcon=new ImageIcon("src/images/search.png");
        //btn
        JButton btnSearch =new JButton("Search",searchIcon);//search button with search icon
        btnSearch.setBounds(10,10,120,35);//setting bounds of the search button
        btnSearch.setBackground(new Color(152, 174, 212));
        frame.getContentPane().add(btnSearch);
        btnSearch .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                participatedRaces.clear();//clear the array list when every time search
                model.setRowCount(0);//set clear the table every time refresh
                String driver=tf.getText();//getting text of the input field
                if(!data.driverNotIn(driver)){//checking the inputted driver is in the list
                    warning.setText("");//remove waring
                    for (Race race: Formula1ChampionshipManager.racesList) {
                        if (race.isInTheRace(driver)){//if driver in the list
                            participatedRaces.add(race);//adding race to the list
                        }
                    }
                }else{
                    warning.setText("Driver not in the List!");
                }

                Object[] row =new Object[2];//a row
                for (Race participatedRace : participatedRaces) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");//formatted date
                    row[0]=simpleDateFormat.format(participatedRace.getRaceDay());//race date
                    row[1] = participatedRace.getPlaceForName(driver);//getting place for the participated race

                    model.addRow(row);// add the row to the table

                }


            }
        });

        frame.setVisible(true);

    }


}
