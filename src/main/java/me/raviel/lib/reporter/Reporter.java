package me.raviel.lib.reporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.plugin.Plugin;

public class Reporter {

    private File folder;
    private Plugin plugin;
    
    public Reporter(Plugin plugin, String folderName){
        this.folder = new File(plugin.getDataFolder(), folderName);
        try{
            if (!folder.exists()){
                folder.mkdirs();
            }
        } catch (SecurityException ex) {
            folder = null;
            ex.printStackTrace();
        }
    }

    public void log(String message){
        if (hasError()) return;
        try {
            Date date = new Date();
            String time = new SimpleDateFormat("dd_MMM_yyyy").format(date);
            String prefix = new SimpleDateFormat("HH:mm:ss").format(date);
            File saveTo = new File(folder, time + ".log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            write(saveTo, new Object[] {String.format("[%s]: %s", prefix, message)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logCF(String message, String fileName){
        if (hasError()) return;
        try {
            String prefix = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
            File saveTo = new File(folder, fileName + ".log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            write(saveTo, new Object[] {String.format("[%s]: %s", prefix, message)});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logCF(List<Object> message, String fileName){
        if (hasError()) return;
        try {
            String prefix = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
            File saveTo = new File(folder, fileName + ".log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            message.add(0, String.format("[%s]: ", prefix));
            write(saveTo, message.toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void log(List<Object> message){
        if (hasError()) return;
        try {
            Date date = new Date();
            String time = new SimpleDateFormat("dd_MMM_yyyy").format(date);
            String prefix = new SimpleDateFormat("HH:mm:ss").format(date);
            File saveTo = new File(folder, time + ".log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            message.add(0, String.format("[%s]: ", prefix));
            write(saveTo, message.toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasError(){
        if (folder == null){
            plugin.getLogger().severe("Fatal error has been occured while trying to log.");
            return true;
        }
        return false;
    }

    private void write(File file, Object[] objs){
        try {
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            if (objs.length > 0){
                for (Object msg : objs){
                    pw.println(msg.toString());
                }
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
