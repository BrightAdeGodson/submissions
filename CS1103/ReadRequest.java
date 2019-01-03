import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * The main() program in this class is designed to read requests from
 * a Web browser and display the requests on standard output.  The
 * program sets up a listener on port 50505.  It can be contacted
 * by a Web browser running on the same machine using a URL of the
 * form  http://localhost:505050/path/to/resource.html  This method
 * does not return any data to the web browser.  It simply reads the
 * request, writes it to standard output, and then closes the connection.
 * The program continues to run, and the server continues to listen
 * for new connections, until the program is terminated (by clicking the
 * red "stop" square in Eclipse or by Control-C on the command line).
 */
public class ReadRequest {

    /**
     * The server listens on this port.  Note that the port number must
     * be greater than 1024 and lest than 65535.
     */
    private final static int LISTENING_PORT = 50505;

    /**
     * Main program opens a server socket and listens for connection
     * requests.  It calls the handleConnection() method to respond
     * to connection requests.  The program runs in an infinite loop,
     * unless an error occurs.
     * @param args ignored
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        }
        catch (Exception e) {
            System.out.println("Failed to create listening socket.");
            return;
        }
        System.out.println("Listening on port " + LISTENING_PORT);
        try {
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
                ConnectionThread thread = new ConnectionThread(connection);
                thread.start();
            }
        }
        catch (Exception e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
        serverSocket.close();
    }

    /**
     * Handle commuincation with one client connection.  This method reads
     * lines of text from the client and prints them to standard output.
     * It continues to read until the client closes the connection or
     * until an error occurs or until a blank line is read.  In a connection
     * from a Web browser, the first blank line marks the end of the request.
     * This method can run indefinitely,  waiting for the client to send a
     * blank line.
     * NOTE:  This method does not throw any exceptions.  Exceptions are
     * caught and handled in the method, so that they will not shut down
     * the server.
     * @param connection the connected socket that will be used to
     *    communicate with the client.
     */
    private static void handleConnection(Socket connection) {
        try {
            Scanner in = new Scanner(connection.getInputStream());
            OutputStream outputStream = connection.getOutputStream();
            String rootDirectory = "./src/www";
            int lineNum = 0;
            String line = "";
            while (true) {
                if (!in.hasNextLine())
                    break;
                line = in.nextLine();
                lineNum++;
                if (line.trim().length() == 0)
                    break;
                if (lineNum == 1)
                    break;
            }
            String[] tokens = line.split(" ");
            String httpRequestType = tokens[0];
            String pathToResource = tokens[1];
            String httpVersion = tokens[2];
            System.out.println("Requst path:" + pathToResource);
            File file = new File(rootDirectory + pathToResource); // handle to file to be sent
            if (!httpRequestType.equals("GET")) {
                sendErrorResponse(501, outputStream);
                in.close();
                connection.close();
                return;
            } else if (!httpVersion.equals("HTTP/1.1")) {
                sendErrorResponse(400, outputStream);
                in.close();
                connection.close();
                return;
            } else if (file.exists()) // check if file exists
                if (!file.isDirectory() && file.length() != 0) {
                    if (file.canRead()) {
                        System.out.println("Response can be sent.");
                        PrintWriter out = new PrintWriter(outputStream);
                        out.print(httpVersion + "200 OK " + "\r\n");
                        out.print("Connection:close" + "\r\n");
                        out.print("Content-Type:" + getMimeType(pathToResource) + "\r\n");
                        out.print("Content-Length:" + file.length() + "\r\n");
                        out.print("\r\n");
                        out.flush();
                        sendFile(file, outputStream); // sends file
                        out.close();
                    } else {
                        sendErrorResponse(403, outputStream);
                        in.close();
                        connection.close();
                        return;
                    }
                } else {
                    sendErrorResponse(404, outputStream);
                    in.close();
                    connection.close();
                    return;
                }
            in.close();
        } catch (Exception e) {
            System.out.println("Error while communicating with client: " + e);
        } finally { // make SURE connection is closed before returning!
            try {
                connection.close();
            } catch (Exception e) {
            }
            System.out.println("Connection closed.");
        }
    }

    /*
     * method to return mime-type
     */
    private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0) // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos + 1).toLowerCase();
        if (ext.equals("txt"))
            return "text/plain";
        else if (ext.equals("html"))
            return "text/html";
        else if (ext.equals("htm"))
            return "text/html";
        else if (ext.equals("css"))
            return "text/css";
        else if (ext.equals("js"))
            return "text/javascript";
        else if (ext.equals("java"))
            return "text/x-java";
        else if (ext.equals("jpeg"))
            return "image/jpeg";
        else if (ext.equals("jpg"))
            return "image/jpeg";
        else if (ext.equals("png"))
            return "image/png";
        else if (ext.equals("gif"))
            return "image/gif";
        else if (ext.equals("ico"))
            return "image/x-icon";
        else if (ext.equals("class"))
            return "application/java-vm";
        else if (ext.equals("jar"))
            return "application/java-archive";
        else if (ext.equals("zip"))
            return "application/zip";
        else if (ext.equals("xml"))
            return "application/xml";
        else if (ext.equals("xhtml"))
            return "application/xhtml+xml";
        else
            return "x-application/x-unknown";
        // Note: x-application/x-unknown is something made up;
        // it will probably make the browser offer to save the file.
    }

    /**
     * method to send file
     *
     * @param file
     * @param socketOut
     * @throws IOException
     */
    private static void sendFile(File file, OutputStream socketOut) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream out = new BufferedOutputStream(socketOut);
        while (true) {
            int x = in.read(); // read one byte from file
            if (x < 0)
                break; // end of file reached
            out.write(x); // write the byte to the socket
        }
        out.flush();
        in.close();
    }

    /*
     * method to send error response
     */
    static void sendErrorResponse(int errorCode, OutputStream socketOut) {
        PrintWriter out = new PrintWriter(socketOut);
        switch (errorCode) {
            case 404:
                out.print("HTTP/1.1" + errorCode + " Not Found " + "\r\n");
                out.print("Connection:close" + "\r\n");
                out.print("Content-Type:" + "text/html" + "\r\n");
                out.print("<html><head><title>Error</title></head><body>" + "<h2>Error: 404 Not Found</h2>"
                        + "<p>The resource that you requested does " + "not exist on this server.</p></body></html>");
                out.print("\r\n");
                out.flush();
                break;
            case 403:
                out.print("HTTP/1.1" + errorCode + " Forbidden " + "\r\n");
                out.print("Connection:close" + "\r\n");
                out.print("Content-Type:" + "text/html" + "\r\n");
                out.print("\r\n");
                out.flush();
                break;
            case 400:
                out.print("HTTP/1.1" + errorCode + " Bad Request " + "\r\n");
                out.print("Connection:close" + "\r\n");
                out.print("\r\n");
                out.flush();
                break;
            case 501:
                out.print("HTTP/1.1" + errorCode + " Not Implemented " + "\r\n");
                out.print("Connection:close" + "\r\n");
                out.print("\r\n");
                out.flush();
                break;
            case 500:
                out.print("HTTP/1.1" + errorCode + " Internal Server Error " + "\r\n");
                out.print("Connection:close" + "\r\n");
                out.print("\r\n");
                out.flush();
                break;
        }
        out.close();
    }

    /**
     *
     * ConnectionThread class
     *
     */
    private static class ConnectionThread extends Thread {
        Socket connection;

        ConnectionThread(Socket connection) {
            this.connection = connection;
        }
        public void run() {
            handleConnection(connection);
        }
    }
}
