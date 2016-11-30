package com.mobin.thread.FutureTask;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Mobin on 2016/11/30.
 * 查找文件中的关键字，每个文件一个线程
 */
public class FileSearchTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String path = "E:\\DATA\\PUBLIC\\NOCE\\AGG\\AGG_MRO_CHR_RELATE\\day=20161020\\hour=2016102011\\vendor=ERS\\10";
        String keyword = "mobin";
        int c = 0;
        File[] files = new File(path).listFiles();
        ArrayList<Future<Integer>> rs = new ArrayList<>();
        for(File file: files){
            MatchCount count = new MatchCount();
            count.file = file;
            count.keyword = keyword;
            FutureTask<Integer> task = new FutureTask(count);
            rs.add(task);
            Thread thread = new Thread(task);
            thread.start();
        }

        for(Future<Integer> f: rs){
            c += f.get();
        }

        System.out.println("包含关键字的总文件数为：" + c);
    }
}

class  MatchCount implements Callable<Integer>{
    public File file;
    public String keyword;
    private  Integer count = 0;

    public Integer call() throws Exception {
        if(search(file))
              count ++;
        return count;
    }

    public boolean search(File file){
        boolean founded = false;
        try(Scanner scanner = new Scanner(new FileInputStream(file))){
            while(!founded && scanner.hasNextLine()){
                if (scanner.nextLine().contains(keyword))
                    founded = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  founded;
    }
}
