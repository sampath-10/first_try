import java.util.*;
import java.net.*;
public class IpAddress{
public static void main(String[] args){
try{
InetAddress localhost=InetAddress.getLocalHost();
System.out.println("Local IP Address: "+localhost.getHostAddress());
System.out.println("Host name : "+localhost.getHostName() );
}
catch(Exception e){
System.out.println("Cannot find ip address");
}
}
}
