package com.example;

public class Remove implements Command{
    private Repository repository;
    public Remove(Repository repository){
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        for( String arg : args) {
            if(repository.findImageByName(arg) != null){
                repository.removeImage(repository.findImageByName(arg));
            }
            else {
                System.out.println("No image found with name " + arg);
            }
        }
    }
}
