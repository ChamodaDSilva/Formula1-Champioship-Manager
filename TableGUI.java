import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

//the class contains the points table with the main menu buttons in swing gui
public class TableGUI {
    TableGUI() {
        JTable table =new JTable();//table making
        Object [] columns ={"Driver","Team","Location","Races","Points","First","Second","Third"};//columns
        DefaultTableModel model=new DefaultTableModel();

        JFrame frame= new JFrame("Point Table By Points Descending");//frame title when opening the gui
        frame.getContentPane().setBackground(new Color(21,41,71));//background of the frame
        frame.getContentPane().setForeground(Color.WHITE);//word color of the pane
        frame.setBounds(100,100,1000,500);//setting height width and bounds in the frame
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exits from the program when the screen closed
        frame.getContentPane().setLayout(null);// no layouts
        frame.setLocationRelativeTo(null);//no relatives

        try {//changed the app icon
            frame.setIconImage(ImageIO.read(new File("src/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.setColumnIdentifiers(columns);//columns adding for the model
        table.getTableHeader().setBackground(new Color(21,41,71));//column header color
        table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,19));//column header
        table.getTableHeader().setForeground(Color.WHITE);//table column name colors
        table.setModel(model);//setting model for the table


        //table.setBackground(new Color(139, 144, 196));
        table.setForeground(Color.black);//word color of the table
        table.setSelectionBackground(new Color(21,41,71));//table selection color
        table.setGridColor(new Color(21,41,71));//grid color setting
        table.setSelectionForeground(Color.white);//word color when row selected
        table.setFont(new Font("Tahoma",Font.PLAIN,17));//setting table font and font size
        table.setRowHeight(30);//row height setting
        table.setAutoCreateRowSorter(true);//activated row auto sorting

        JScrollPane pane= new JScrollPane(table);//added the table to the pain
        pane.setForeground(Color.blue);//pane word color
        pane.setBackground(Color.blue);//pane background color
        pane.setBounds(150,0,850,600);//pane bounds and height width
        frame.getContentPane().add(pane);

        Collections.sort(Formula1ChampionshipManager.formula1DriversList,new PointComparator().reversed());//sorted once before show in point vise descending
        Object[] row =new Object[8];//row
        for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
            row[0]=formula1Driver.getName();//name
            row[1]=formula1Driver.getTeam();//team
            row[2]=formula1Driver.getLocation();//location
            row[3]=formula1Driver.getNumOfRaces();//number of races
            row[4]=formula1Driver.getNumOfPoints();//number of points
            row[5]=formula1Driver.getFirstPosition();//number of first positions
            row[6]=formula1Driver.getSecondPosition();//number of second positions
            row[7]=formula1Driver.getThirdPosition();//number of third positions

            model.addRow(row);//adding row to the model
        }

        //title of the app for icon
        ImageIcon titleIcon=new ImageIcon("src/images/car.png");//the image icon to set int the label
        JLabel title=new JLabel();//label to kee the image
        title.setBounds(15,15,120,100);//label bounds
        title.setIcon(titleIcon);//setting icon to the label
        frame.getContentPane().add(title);//added label to the frame

        //btn ascending to keep drivers ascending order for the points
        JButton btnAscending =new JButton("Ascending");//btn ascending
        btnAscending.setBounds(15,140,120,35);//bounds of the button
        btnAscending.setBackground(new Color(152, 174, 212));//set background color of the button
        frame.getContentPane().add(btnAscending);//added button to the frame
        btnAscending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Point Table By Points Ascending");//change the frame title according to the button
                model.setRowCount(0);//clear the past records from the table
                Collections.sort(Formula1ChampionshipManager.formula1DriversList,new PointComparator());//make it in ascending order by points
                for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
                    row[0]=formula1Driver.getName();//name
                    row[1]=formula1Driver.getTeam();//team
                    row[2]=formula1Driver.getLocation();//location
                    row[3]=formula1Driver.getNumOfRaces();//number of races
                    row[4]=formula1Driver.getNumOfPoints();//number of points
                    row[5]=formula1Driver.getFirstPosition();//number of first positions
                    row[6]=formula1Driver.getSecondPosition();//number of second positions
                    row[7]=formula1Driver.getThirdPosition();//number of third positions

                    model.addRow(row);//added row to the model
                }
            }
        });

        //btn descending first place
        JButton btnFirstPosition =new JButton("By First Places");// btn first places
        btnFirstPosition.setBounds(15,185,120,35);//setting bounds of the button
        btnFirstPosition.setBackground(new Color(152, 174, 212));// setting background color of the button
        frame.getContentPane().add(btnFirstPosition);// add the button to the frame
        btnFirstPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Point Table By First Place");// setting title
                model.setRowCount(0);//clear the past records from the table
                Collections.sort(Formula1ChampionshipManager.formula1DriversList,new FirstComparator().reversed());//make it in descending order for first places
                for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
                    row[0]=formula1Driver.getName();//name
                    row[1]=formula1Driver.getTeam();//team name
                    row[2]=formula1Driver.getLocation();//location
                    row[3]=formula1Driver.getNumOfRaces();//number of races
                    row[4]=formula1Driver.getNumOfPoints();//number of points
                    row[5]=formula1Driver.getFirstPosition();//number of first places
                    row[6]=formula1Driver.getSecondPosition();//number of second places
                    row[7]=formula1Driver.getThirdPosition();//number of third places

                    model.addRow(row);//adding row to the model
                }
            }
        });

        //btn randomRace with random starting positions and random ending positions
        JButton btnRandomRace =new JButton("Random Race");//btn random race
        btnRandomRace.setBounds(15,230,120,35);//setting bounds of the button with the shape
        btnRandomRace.setBackground(new Color(152, 174, 212));//set background color of the button
        frame.getContentPane().add(btnRandomRace);// adding btn to the frame
        btnRandomRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RandomGUI();//opening random race option 1
                frame.setVisible(false);//closing the points table
            }
        });

        //btn randomRace to go a random race with some probability for first place with starting position
        JButton btnRandomRace2 =new JButton("Random Race2");//btn random race option 2
        btnRandomRace2.setBounds(15,275,120,35);//button size and the bounds
        btnRandomRace2.setBackground(new Color(152, 174, 212));//color of the button
        frame.getContentPane().add(btnRandomRace2);//adding button to the race
        btnRandomRace2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RandomGUI2();// opening random race option 2
                frame.setVisible(false);// and closing points table

            }
        });
        //btn randomRace
        JButton btnViewRace =new JButton("Races");//btn races
        btnViewRace .setBounds(15,320,120,35);//shape of the button and the bounds
        btnViewRace.setBackground(new Color(152, 174, 212));//background color of the button
        frame.getContentPane().add(btnViewRace);//adding button to the frame
        btnViewRace .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RaceDetailsGUI();//opening past races showing gui
            }
        });

        // searching button for drivers for positions
        JButton btnSearch =new JButton("Search Driver");//btn search driver
        btnSearch .setBounds(15,365,120,35);//bounds of the driver
        btnSearch.setBackground(new Color(152, 174, 212));//btn background color
        frame.getContentPane().add(btnSearch);//adding btn to the frame to display
        btnSearch .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchRaceGUI();//opening search race gui
            }
        });

        //point table making descending according to the points if points are equal sort with first places
        JButton btnDescending =new JButton("Descending");//btn descending
        btnDescending.setBounds(15,410,120,35);//button shape and bounds
        btnDescending.setBackground(new Color(152, 174, 212));//background color of the button
        frame.getContentPane().add(btnDescending);//adding button to the frame
        btnDescending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("Point Table By Points Descending");//set frame title according to the button
                model.setRowCount(0);//clear the past records from the table
                Collections.sort(Formula1ChampionshipManager.formula1DriversList,new PointComparator().reversed());//sorted once before show

                for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
                    row[0]=formula1Driver.getName();//name
                    row[1]=formula1Driver.getTeam();//team
                    row[2]=formula1Driver.getLocation();//location
                    row[3]=formula1Driver.getNumOfRaces();//number of races
                    row[4]=formula1Driver.getNumOfPoints();//number of points
                    row[5]=formula1Driver.getFirstPosition();//number of first places
                    row[6]=formula1Driver.getSecondPosition();//number of second places
                    row[7]=formula1Driver.getThirdPosition();//number of third positions

                    model.addRow(row);// add a row for the model with these above values
                }
            }
        });


        frame.setVisible(true);//set visible the window

    }

}
