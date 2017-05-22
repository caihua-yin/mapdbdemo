package com.caihua.mapdbdemo;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import java.util.concurrent.ConcurrentMap;

/**
 * Hello world!
 *
 */
public class App 
{
    static void offHeapDemo() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("key", "off heap");
        System.out.println(map.get("key"));
    }

    static void fileDemo() {
        DB db = DBMaker.fileDB("file.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("key", "file");
        System.out.println(map.get("key"));
        db.close();
    }

    static void mmapDemo() {
        DB db = DBMaker.fileDB("file-mmap.db").fileMmapEnable().make();
        ConcurrentMap<String,Long> map = db.hashMap("map", Serializer.STRING, Serializer.LONG)
            .createOrOpen();
        map.put("key", 111L);
        System.out.println(map.get("key"));
        db.close();
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        offHeapDemo();
        fileDemo();
        mmapDemo();
    }
}
