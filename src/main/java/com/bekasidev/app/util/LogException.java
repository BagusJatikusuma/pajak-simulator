package com.bekasidev.app.util;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogException {

    public LogException(SQLException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(IOException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(FileNotFoundException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(JRException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(MalformedURLException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(URISyntaxException e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }

    public LogException(Exception e) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("exception.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat fd = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        pw.write(fd.format(new Date()) + " ");
        e.printStackTrace (pw);
        try {
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
    }
}
