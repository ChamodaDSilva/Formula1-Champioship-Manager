import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

//this class contains random race option 2 with swing gui
public class RandomGUI2 {
    RandomGUI2(){
        JTable table =new JTable();//table
        Object [] columns ={"Driver","Team Name","Staring Place","Place"};//columns
        DefaultTableModel model=new DefaultTableModel();

        JFrame frame= new JFrame("Random Race 2");//setting frame heading while making the object
        frame.getContentPane().setBackground(new Color(21,41,71));//background color of the pane
        frame.getContentPane().setForeground(Color.WHITE);// word colors
        frame.setBounds(100,100,1000,500);//setting bounds od the frame

        frame.getContentPane().setLayout(null);// no any layouts
        frame.setLocationRelativeTo(null);// no any relatives

        try {//changed the app icon
            frame.setIconImage(ImageIO.read(new File("src/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel dateLabel=new JLabel();//date label
        dateLabel.setBounds(140,10,200,30);//date label bounds setting
        dateLabel.setForeground(Color.white);
        frame.getContentPane().add(dateLabel);//added the label to the frame


        ImageIcon backIcon=new ImageIcon("src/images/back.png");
        //back button
        JButton btnBack =new JButton("Back",backIcon);
        btnBack.setBounds(10,10,120,30);
        btnBack.setBackground(new Color(152, 174, 212));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableGUI();//open the table
                frame.setVisible(false);//and close the recent tab for be back

            }
        });

        model.setColumnIdentifiers(columns);
        table.getTableHeader().setBackground(new Color(21,41,71));//column header color
        table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,19));//column header
        table.getTableHeader().setForeground(Color.WHITE);//table column name colors
        table.setModel(model);

        table.setBackground(new Color(139, 144, 196));//table background color
        table.setForeground(Color.black);//table word color setting
        table.setSelectionBackground(Color.BLUE);//table selection background color
        table.setGridColor(new Color(21,41,71));//color of a grid
        table.setSelectionForeground(Color.white);//table selection word color
        table.setFont(new Font("Tahoma",Font.PLAIN,17));//font and the size setting of the table words
        table.setRowHeight(30);//setting row height
        table.setAutoCreateRowSorter(true);//set row sorter on

        JScrollPane pane= new JScrollPane(table);//added scroll pane to th table
        pane.setForeground(Color.BLUE);// pane color background
        pane.setBackground(Color.WHITE);//pane color foreground
        pane.setBounds(0,50,1000,450);//set pna bounds
        frame.getContentPane().add(pane);

        //shuffling the participants for starting position
        Formula1ChampionshipManager data=new Formula1ChampionshipManager();//object made for use some methods already in that object
        Formula1Driver [] participants =new Formula1Driver[Formula1ChampionshipManager.formula1DriversList.size()];//array for keep drivers in parallel for the starting position index +1
        Collections.shuffle(Formula1ChampionshipManager.formula1DriversList);//shuffling the drivers list to get random starting positions

        int count=0;
        for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
            participants[count]=formula1Driver;// keeping in the starting position with driver
            count++;
        }

        ArrayList<Integer> places= endingPositions(Formula1ChampionshipManager.formula1DriversList.size());// shuffled integers for winning positions with probability for first positions

        Formula1Driver [] driverSortedWithPlace=new Formula1Driver[Formula1ChampionshipManager.formula1DriversList.size()];//to store drivers parallel to the won place to the index

        Object[] row =new Object[4];// to keep a row

        for (int i = 0; i< Formula1ChampionshipManager.formula1DriversList.size(); i++) {
            row[0]=participants[i].getName();//
            row[1]=participants[i].getTeam();
            row[2]=i+1;//the starting place
            row[3]=places.get(i);//get random position

            Formula1ChampionshipManager.formula1DriversList.get(i).setNumOfPointsByPlace(places.get(i));//pass the stats for driver
            driverSortedWithPlace[places.get(i) -1]= Formula1ChampionshipManager.formula1DriversList.get(i);//assign to the race drivers values

            model.addRow(row);// add the row to the table

        }

        Random random=new Random();
        String dateSample = "/09/2021";
        String dateInString=random.nextInt(100)+1+ dateSample;//getting a random date after 2021 september
        //adding the race to the list
        try {
            Formula1ChampionshipManager.racesList.add(new Race(dateInString,driverSortedWithPlace));//add date to the race details
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //setting the date label
        try {
            Date raceDay = new SimpleDateFormat("dd/MM/yyyy").parse(dateInString);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");//formatted date
            dateLabel.setText("Date: "+simpleDateFormat.format(raceDay));//setting date label text with the random date
        } catch (ParseException e) {
            e.printStackTrace();
        }

        data.saveData();// to save data to the file
        data.saveRaceData();//save race data
        frame.setVisible(true);//set visible the frame

    }

    private ArrayList<Integer> endingPositions(int length){//this method returns the won positions wih the
        int firstPlace;// who got the first place(Starting position)
        if(percent(2)){//2 percent for 9 starting place
            firstPlace=9;
        }else if(percent(2)){//2 percent for 8 starting place
            firstPlace=8;
        }else if(percent(2)){//2 percent for 7 starting place
            firstPlace=7;
        }else if(percent(2)){//2 percent for 6 starting place
            firstPlace=6;
        }else if(percent(2)){//2 percent for 5 starting place
            firstPlace=5;
        }else if(percent(10)){//10 percent for 4 starting place
            firstPlace=4;
        }else if(percent(10)){//10 percent for 3 starting place
            firstPlace=3;
        }else if(percent(30)){//30 percent for 2 nd starting place
            firstPlace=2;
        }else{//40 percent for 1 st starting place
            firstPlace=1;
        }
        //to get shuffled unique numbers to a given number
        ArrayList<Integer> list = new ArrayList<>();//for store winning positions parallel for the starting place index
        for (int i=2; i<length+1; i++) {
            list.add(i);//adding places without fist place
        }
        Collections.shuffle(list);
        list.add(firstPlace-1,1);//adding first place for whom achieved with the percentage/ index is starting position -1
        return list;//returning winning list
    }
    private Boolean percent(int howPercentage){
        //this method returns the true when is percentage got the first place
        Random random=new Random();
        int num=0;
        num = random.nextInt(100) + 1;//get the random number
        if(howPercentage>=num){//and checking if it is in the percentage
            return true;//if won first
        }
        return false;//if lost

    }
}


