import java.util.*;
class theatre
{
    String theatre_name;
    String location;
    ArrayList<screens>screens;
    theatre(String name,String loc,ArrayList<screens>s)
    {
        this.theatre_name=name.toUpperCase();
        this.location=loc.toUpperCase();
        this.screens=s;
    }
    void display()
    {
        System.out.println(theatre_name.toUpperCase());
        for(screens s:screens)
        {
            s.display();
        }
    }
}
class screens
{
    int screen_no;
    ArrayList<shows>shows;
    screens(int num,ArrayList<shows>s)
    {
        this.screen_no = num;
        this.shows=s;
    }
    void display()
    {
        for(shows s:shows)
        {
            System.out.println("Screen_Number:"+screen_no+" "+s.movie_name.toUpperCase());
            s.display_seat();
            System.out.println("--------------------");
        }
    }
    void display_timing()
    {
        for(shows s:shows)
        {
            s.display_timing();
        }

    }
}
class shows
{
    String movie_name,Show_timing;
    HashMap<String,Boolean>isavail=new HashMap<>();
    HashMap<String,person>details=new HashMap<>();
    int number_of_tickets=10;
    void fill_map()
    {
        for(int i=1;i<6;i++)
        {
            for(int j=1;j<3;j++)
            {
                String row=String.valueOf(i);
                String col=String.valueOf(j);
                isavail.put(row+" "+col,true);
            }
        }
    }
    shows(String movie_name,String show_timing)
    {
        this.movie_name=movie_name;
        this.Show_timing=show_timing;
    }
    void display_seat()
    {
        System.out.println("Show_Timing: "+Show_timing);
        System.out.println("NUMBER_OF_AVAILABLE_TICKETS:"+number_of_tickets);
        for(int i=1;i<6;i++)
        {
            for(int j=1;j<3;j++)
            {
                System.out.print(i+" "+j+"     ");
            }
            System.out.println();
        }
    }
    void display_timing()
    {
        System.out.println(Show_timing);
    }
    boolean ticketavail(String seat_no)
    {
        if(isavail.get(seat_no)==null)return false;
        else return isavail.get(seat_no);
    }
}
class person
{
    String name,password,phone_num;
    ArrayList<ticket>tickets=new ArrayList<>();
    person(String name,String pass,String phone_num)
    {
        this.name=name;
        this.password=pass;
        this.phone_num=phone_num;
    }
    void add_ticket(ticket t)
    {
        tickets.add(t);
    }
    void display_tickets()
    {
        if(tickets.size()==0)
        {
            System.out.println("NO BOOKED TICKETS AVAILABLE");
        }
        else
        {
            for (ticket t : tickets) {
                t.display();
                System.out.println("----------------");
            }
        }
    }
}
class ticket
{
    String tick_id,theatre_name,theatre_location,show_timing,screen_no,seat_no;
    ticket(String tick_id,String theatre_name,String theatre_location,String show_timing,String screen_no,String seat_no)
    {
        this.tick_id=tick_id;
        this.theatre_location=theatre_location;
        this.theatre_name=theatre_name;
        this.show_timing=show_timing;
        this.screen_no=screen_no;
        this.seat_no=seat_no;
    }
    void display()
    {
        System.out.println("TICKET_ID:"+tick_id);
        System.out.println("THEATRE_NAME:"+theatre_name);
        System.out.println("THEATRE_LOCATION"+theatre_location);
        System.out.println("SHOW_TIMING:"+show_timing);
        System.out.println("SCREEN_NUMBER:"+screen_no);
        System.out.println("SEAT_NUMBER:"+seat_no);
    }
}
public class bookmyshow
{
    static int tick_id=100;
    static ArrayList<person>person=new ArrayList<>();
    static ArrayList<theatre>theatres=new ArrayList<>();
     static Scanner in=new Scanner(System.in);
     static boolean theatre_find(String theatre_name)
     {
         for(theatre t:theatres)
         {
             if(t.theatre_name.equalsIgnoreCase(theatre_name))
             {
                 return true;
             }
         }
         return false;
     }
     static void change_movie()
     {
         for(theatre t:theatres)
         {
             System.out.println(t.theatre_name);
         }
         System.out.println("ENTER THEATRE NAME:");
         String theatre_name= in.nextLine();
         boolean theatrefind=theatre_find(theatre_name);
         if(theatrefind)
         {
             for(theatre t:theatres)
             {
                 if(t.theatre_name.equals(theatre_name))
                 {
                     System.out.println("ENTER THE SCREEN NUMBER:");
                     String screen_number= in.nextLine();
                     for(screens s:t.screens)
                     {
                         if(s.screen_no==Integer.parseInt(screen_number))
                         {
                             System.out.println("1.CHANGE FULL SHOWS \n2.CHANGE ONLY ONE SHOW \n3.EXIT");
                             String choice= in.nextLine();
                             if(choice.equals("1"))
                             {
                                 System.out.println("ENTER MOVIE NAME");
                                 String moviech_name=in.nextLine();
                                 for(shows st:s.shows)
                                 {
                                     st.movie_name=moviech_name;
                                 }
                                 System.out.println("MOVIE NAME CHANGED SUCESSFULLY FOR ALL TIMINGS");
                             }
                             if(choice.equals("2"))
                             {
                                 s.display_timing();
                                 System.out.println("ENTER A TIMING TO CHANGE MOVIE:");
                                 String timing= in.nextLine();
                                 System.out.println("ENTER A MOVIE NAME:");
                                 String moviech_name=in.nextLine();
                                 boolean changed=true;
                                 for(shows st:s.shows)
                                 {
                                     if(st.Show_timing.equals(timing))
                                     {
                                         st.movie_name=moviech_name;
                                         changed=false;
                                     }
                                 }
                                 if(changed)
                                 {
                                     System.out.println("NO TIMINGS FOUND UNABLE TO CHANGE RETRY");
                                 }

                             }
                         }
                     }
                 }
             }

         }
         else
         {
             System.out.println("NO THEATRE FOUND");
         }
     }
     static void add_theatre()
     {
         System.out.println("ENTER THEATRE NAME:");
         String theatre_name= in.nextLine();
         System.out.println("ENTER THEATRE LOCATION:");
         String theatre_loc=in.nextLine();
         System.out.println("ENTER NUMBER OF SCREENS:");
         String num_of_screens= in.nextLine();
         ArrayList<screens>screensArrayList=new ArrayList<>();
         for(int i=0;i<Integer.parseInt(num_of_screens);i++)
         {
             ArrayList<shows>showsArrayList=new ArrayList<>();
             System.out.println("ENTER NUMBER OF SHOWS");
             String num_of_shows= in.nextLine();
             for(int j=0;j<Integer.parseInt(num_of_shows);j++)
             {
                 System.out.println("ENTER THE MOVIE NAME:");
                 String movie_name= in.nextLine();
                 System.out.println("ENTER THE SHOW TIMING:");
                 String show_timing= in.nextLine();
                 showsArrayList.add(new shows(movie_name,show_timing));
             }
             screensArrayList.add(new screens(i+1,showsArrayList));
         }
         System.out.println("THEATRE ADDED SUCESSFULLY");
         theatres.add(new theatre(theatre_name,theatre_loc,screensArrayList));
         for(theatre t:theatres) {
             if(t.theatre_name.equals(theatre_name)) {
                 for (screens s : t.screens) {
                     for (shows st : s.shows) {
                         st.fill_map();
                     }
                 }
                 break;
             }
         }

     }
     static void display_theatre()
     {
         HashMap<Integer,String>map=new HashMap<>();
         System.out.println("ENTER THEATRE NAME:");
         int i=1;
         for(theatre t:theatres)
         {
             System.out.println(i+"."+t.theatre_name);
             map.put(i,t.theatre_name);
             i++;
         }
         String choice= in.nextLine();
         String name=map.get(Integer.parseInt(choice));
         for(theatre t:theatres)
             if(t.theatre_name.equalsIgnoreCase(name))
             {
                 t.display();
             }
     }
     static void admin()
     {
         boolean admin_flag=true;
         while(admin_flag) {
             System.out.println("1.CHANGE MOVIE \n2.ADD THEATRE \n3.DISPLAY_THEATRE \n4.LOGOUT");
             String choice = in.nextLine();
             if (choice.equals("1")) change_movie();
             if (choice.equals("2")) add_theatre();
             if(choice.equals("3"))display_theatre();
             if (choice.equals("4"))
             {
                 admin_flag=false;
                 System.out.println("LOGGED OUT SUCCESSFULLY");
                 System.out.println("-----------------------");
             }
         }

     }
     static HashMap<Integer,String> movies_display()
     {
         HashMap<Integer,String>map=new HashMap<>();
         HashSet<String>set=new HashSet<>();
         for(theatre t:theatres)
         {
             for(screens s:t.screens)
             {
                 for(shows st:s.shows)
                 {
                     set.add(st.movie_name);
                 }
             }
         }
         int i=1;
         for(String s:set)
         {
             System.out.println(i+"."+s);
             map.put(i,s);
             i++;
         }
         System.out.println("-----------------");
         return map;
     }
     static String person_login()
     {
         System.out.println("ENTER YOUR NAME: ");
         String name=in.nextLine();
         System.out.println("ENTER YOU PASSWORD:");
         String pass=in.nextLine();
         System.out.println("------------------");
         for(person p:person)
         {
             if(p.name.equals(name) && p.password.equals(pass))
             {
                 return name;
             }
         }
         return "";
     }
     static void signup()
     {
         System.out.println("ENTER YOUR NAME:");
         String name=in.nextLine();
         System.out.println("ENTER YOUR PASSWORD:");
         String pass=in.nextLine();
         System.out.println("ENTER YOUR PHONE NUMBER:");
         String phone_num=in.nextLine();
         person.add(new person(name,pass,phone_num));
     }
     static void timings_display(String movie_name,String theatre_name)
     {
         for(theatre t:theatres)
         {
             if(t.theatre_name.equals(theatre_name))
             {
                 for (screens s : t.screens)
                 {
                     for (shows st : s.shows)
                     {
                         if (st.movie_name.equals(movie_name))
                         {
                             System.out.println("SCREEN NUMBER: "+s.screen_no+" ");
                             System.out.println("SCREEN TIMING: "+st.Show_timing+" ");
                         }
                     }
                 }
             }
         }
     }
     static String theatre_movie_display(String movie_name)
     {
         HashMap<Integer,String>map=new HashMap<>();
         HashSet<String>set=new HashSet<>();
         for(theatre t:theatres)
         {
             for(screens s:t.screens)
             {
                 for(shows st:s.shows)
                 {
                     if(st.movie_name.equals(movie_name))
                     {
                         set.add(t.theatre_name);
                     }
                 }
             }
         }
         int i=1;
         for(String str:set)
         {
             System.out.println(i+"."+str);
             for(theatre t:theatres)
             {
                 if(t.theatre_name.equals(str))
                 {
                     System.out.println("LOCATION: "+t.location);
                     break;
                 }
             }
             timings_display(movie_name,str);
             System.out.println("-------------------------------");
             map.put(i,str);
             i++;
         }
         System.out.println("ENTER YOUR THEATRE_NUMBER:");
         String choice=in.nextLine();
         return map.get(Integer.parseInt(choice));
     }
     static void book_ticket(String per_name,String movie_name,String theatre_name)
     {
         timings_display(movie_name,theatre_name);
         System.out.println("ENTER YOUR TIMING:");
         String timing=in.nextLine();
         for(theatre t:theatres)
         {
             if(t.theatre_name.equals(theatre_name))
             {
                 for (screens s : t.screens)
                 {
                     for (shows st : s.shows)
                     {
                         if (st.movie_name.equals(movie_name) && st.Show_timing.equals(timing))
                         {
                            st.display_seat();
                            System.out.println("ENTER NUMBER OF SEATS:");
                            int num= Integer.parseInt(in.nextLine());
                            while(num>0)
                            {
                                System.out.println("ENTER SEAT_NUMBER:");
                                String seat_no=in.nextLine();
                                if(st.ticketavail(seat_no))
                                {
                                    for(person p:person)
                                    {
                                        if (p.name.equals(per_name))
                                        {
                                            p.add_ticket(new ticket(String.valueOf(tick_id),t.theatre_name,t.location,st.Show_timing,String.valueOf(s.screen_no),seat_no));
                                            st.isavail.put(seat_no,false);
                                            st.details.put(seat_no,p);
                                            st.number_of_tickets--;
                                            System.out.println("TICKET IS BOOKED");
                                            num--;
                                            tick_id++;
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("SORRY THAT TICKET IS ALREADY BOOKED");
                                }

                            }
                         }
                     }
                 }
             }
         }
     }
     static void book_ticket_0(String name)
     {
         HashMap<Integer,String>map=movies_display();
         System.out.println("ENTER THE MOVIE NUMBER:");
         String movie_number=in.nextLine();
         String movie_name=map.get(Integer.parseInt(movie_number));
         String theatre_name=theatre_movie_display(movie_name);
         book_ticket(name,movie_name,theatre_name);
     }
     static void view_ticket(String name)
     {
         for(person p:person)
         {
             if(p.name.equals(name))
             {
                 p.display_tickets();
                 break;
             }
         }
     }
     static void map_put(ticket t)
     {
         for(theatre th:theatres)
         {
             if(t.theatre_name.equals(th.theatre_name))
             {
                 for(screens s:th.screens)
                 {
                     if(s.screen_no==Integer.parseInt(t.screen_no))
                     {
                         for(shows show:s.shows)
                         {
                             if(show.Show_timing.equals(t.show_timing))
                             {
                                 show.isavail.put(t.seat_no,true);
                                 show.number_of_tickets++;
                             }
                         }
                     }
                 }
             }
         }
     }
     static void delete_all_tickets(String name)
     {
         for(person p:person)
         {
             if(p.name.equals(name))
             {
                 for(ticket t: p.tickets)
                 {
                     map_put(t);
                 }
             }
         }
         for(person p:person)
         {
             if(p.name.equals(name))
             {
                 p.tickets.clear();
             }
         }
         System.out.println("CANCELLED TICKETS SUCESSFULLY");
     }

     static void cancel_ticket(String name)
     {
         for(person p:person)
         {
             if(p.name.equals(name))
             {
                 delete_all_tickets(name);
                 break;
             }
         }
     }
     static void login_stage2(String name)
     {
         boolean flag=true;
         while(flag)
         {
             System.out.println("1.BOOK TICKET \n2.VIEW TICKET \n3.CANCEL TICKET \n4.LOGOUT");
             System.out.println("-----------------------------");
             String choice = in.nextLine();
             if (choice.equals("1"))book_ticket_0(name);
             if(choice.equals("2"))view_ticket(name);
             if(choice.equals("3"))cancel_ticket(name);
             if(choice.equals("4"))flag=false;
         }
     }
     static void person()
     {
         boolean login=true;
         while(login)
         {
             System.out.println("1.LOGIN \n2.SIGNUP \n3.LOGOUT");
             System.out.println("------------------------------");
             String choice= in.nextLine();
             String user;
             if(choice.equals("1"))
             {
                 user=person_login();
                 if(user.equals(""))System.out.println("INCORRECT NAME OR PASSSWORD");
                 else login_stage2(user);
             }
             if(choice.equals("2"))signup();
             if(choice.equals("3"))login=false;
         }
     }
    public static void main(String[] args)
    {
        System.out.println("WELCOME TO BOOK MYSHOW CONSOLE APPLICATION");
        ArrayList<screens>temp1=new ArrayList<>();
        ArrayList<shows>showsarray=new ArrayList<>();
        showsarray.add(new shows("THUNIVU","09.00-12.00"));
        showsarray.add(new shows("VARISU","02.00-04.00"));
        showsarray.add(new shows("THUNIVU","06.00-09.00"));
        temp1.add(new screens(1,showsarray));
        theatre t1=new theatre("THEATRE1","SOMANUR",temp1);
        theatres.add(t1);
        ArrayList<screens>temp2=new ArrayList<>();
        ArrayList<shows>showsarray2=new ArrayList<>();
        showsarray2.add(new shows("RA-ONE","09.00-12.00"));
        showsarray2.add(new shows("NARUTO","02.00-04.00"));
        showsarray2.add(new shows("THUNIVU","06.00-09.00"));
        temp2.add(new screens(1,showsarray2));
        theatre t2=new theatre("THEATRE2","SOMANUR",temp2);
        theatres.add(t2);
        for(theatre t:theatres) {
            for(screens s:t.screens)
            {
                for(shows st:s.shows)
                {
                    st.fill_map();
                }
            }
        }
        person.add(new person("SURIYA","123","123"));
        boolean main_flag=true;
        while(main_flag)
        {
            System.out.println("1.ADMIN \n2.PERSON \n3.EXIT");
            System.out.println("---------------------------");
            String choice=in.nextLine();
            if(choice.equals("1"))admin();
            if(choice.equals("2"))person();
            if(choice.equals("3"))main_flag=false;
            if(!choice.matches("[123]"))System.out.println("INVALID COMMAND");
        }
    }
}