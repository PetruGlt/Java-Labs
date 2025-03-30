package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Update implements Command {
    private Repository repository;
    private Image image;

    public Update(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        String nameToUpdate =args[0];
        String newName = args[1];
        args = Arrays.copyOfRange(args, 2, args.length);
        if(repository.findImageByName(nameToUpdate) != null) {
            image = repository.findImageByName(nameToUpdate);
            var tags = image.tags();
            var path = image.path();
            repository.removeImage(image);
            repository.addImage(new Image(newName, path));
            image = repository.findImageByName(newName);
            image.setTags(tags);
            if(args.length > 0){
                image.setTags(Arrays.asList(args));
            }

        }
    }
}
