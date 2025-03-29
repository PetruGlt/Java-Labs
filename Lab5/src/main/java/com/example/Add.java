package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Add implements Command{
    private Repository repository;

    public Add(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        String name = args[0];
        String path = args[1];


        Image image = new Image(name, path);

        if(args.length > 2) {
            List<String> tags = Arrays.asList(Arrays.copyOfRange(args, 2, args.length));
            image.addTags(tags);
        }


        repository.addImage(image);
        System.out.println("Image added to repository: "+ image);



    }
}
