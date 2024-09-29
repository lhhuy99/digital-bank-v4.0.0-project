package vn.funix.fx13483.java.asm04.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryFileServiceTest {

    @Test
    public void readFile() {
        List<String> list = new ArrayList<>();
        list.add("test");
        BinaryFileService.writeFile("abc.dat", list);
        assertEquals(list, BinaryFileService.readFile("abc.dat"));
    }

    @Test
    public void writeFile() {
        List<String> list = new ArrayList<>();
        list.add("test");
        BinaryFileService.writeFile("abc.dat", list);
        assertEquals(list, BinaryFileService.readFile("abc.dat"));
    }
}