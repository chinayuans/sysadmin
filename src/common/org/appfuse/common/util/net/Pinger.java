/*BEGIN OCO COPYRIGHT
 *************************************************************************
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724-L01, 5655-N53, 5724-I82, 5655-R15
 * (C) Copyright IBM Corporation 2006, 2007.
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 *
 **************************************************************************
 *END OCO COPYRIGHT
 */

package org.appfuse.common.util.net;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

public class Pinger {
    public static void main(String[] args) {
        System.out.println(ping("9.181.85.188", 50000, 2000));
        System.out.println(isReachable2("9.181.85.188", 50000));
        System.out.println(isReachable1("9.181.85.188", 50000));
        System.out.println(isReachable3("9.181.85.188", 50000));
        
        System.out.println("=====================================");
        System.out.println(ping("9.181.85.188", 7, 2000));
        System.out.println(isReachable2("9.181.85.188", 7));
        System.out.println(isReachable1("9.181.85.188", 7));
        System.out.println(isReachable3("9.181.85.188", 7));
    }

    public static boolean isReachable1(String hostName, int port) {
        boolean success = false;
        Socket socket = null;
        try {
            InetAddress address = InetAddress.getByName(hostName);
            socket = new Socket(address, port);
            success = socket.isConnected();
            System.out.println("success:" + success);
        } catch (UnknownHostException e) {
            System.out.println("exception:" + e);
            success = false;
        } catch (IOException e) {
            System.out.println("exception:" + e);
            success = false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("exception:" + e);
                }
            }
        }

        return success;
    }

    public static boolean isReachable3(String hostName, int port) {
        boolean success = false;
        Socket socket = new Socket();
        try {
            InetAddress address = InetAddress.getByName(hostName);
            SocketAddress scAddress = new InetSocketAddress(address, port);
            socket.connect(scAddress, 2000);
            success = socket.isConnected();
            System.out.println("success:" + success);
        } catch (UnknownHostException e) {
            System.out.println("exception:" + e);
            success = false;
        } catch (IOException e) {
            System.out.println("exception:" + e);
            success = false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("exception:" + e);
                }
            }
        }
        return success;
    }

    public static boolean isReachable2(String hostName, int port) {
        boolean success = false;
        SocketChannel socketChannel = null;
        try {
            InetAddress address = InetAddress.getByName(hostName);
            InetSocketAddress scAddress = new InetSocketAddress(address,port);
            
            // Open the channel, set it to non-blocking, initiate connect
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(scAddress);
            success=socketChannel.isConnected();
        } catch (UnknownHostException e) {
            System.out.println("exception:" + e);
            success = false;
        } catch (IOException e) {
            System.out.println("exception:" + e);
            success = false;
        } finally {
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    System.out.println("exception:" + e);
                }
            }
        }

        try {
            if (socketChannel.finishConnect()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            try {
                socketChannel.close();
            } catch (IOException ex) {
                System.out.println("exception:" + ex);
            }
        }
        
        return success;
    }

    public static long ping(String host, int port, int timeoutMs) {
        // pass in a byte array with the ipv4 address, the port & the max
        // timeout required
        long startTime = -1; // default check value
        long endTime = -1; // default check value
        long totalTime = -1; // default for bad connection

        // make an unbound socket
        Socket socket = new Socket();

        try {
            InetAddress addr = InetAddress.getByName(host);
            SocketAddress sockaddr = new InetSocketAddress(addr, port);

            // Create the socket with a timeout
            // when a timeout occurs, we will get timout exp.
            // also time our connection this gets very close to the real time
            startTime = System.currentTimeMillis();

            socket.connect(sockaddr, timeoutMs);

            endTime = System.currentTimeMillis();
        } catch (UnknownHostException e) {
            startTime = -1;
            endTime = -1;
        } catch (SocketTimeoutException e) {
            startTime = -1;
            endTime = -1;
        } catch (IOException e) {
            startTime = -1;
            endTime = -1;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }

            if ((startTime != -1) && (endTime != -1)) {
                totalTime = endTime - startTime;
            }
        }

        return totalTime; // returns -1 if timeout
    }
}
