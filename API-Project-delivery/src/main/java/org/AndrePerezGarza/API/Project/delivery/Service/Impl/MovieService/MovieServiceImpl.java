package org.AndrePerezGarza.API.Project.delivery.Service.Impl.MovieService;

import lombok.extern.slf4j.Slf4j;
import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.AndrePerezGarza.API.Project.delivery.Mapper.MovieMapper;
import org.AndrePerezGarza.API.Project.delivery.Service.MovieService;
import org.AndrePerezGarza.API.Project.delivery.Repository.MovieRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private MovieMapper mapper;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movies = repository.findAll();
        log.info("Converting Movie a MovieDTO");
        log.info("Returning all MovieDTO Objects");
        return movies.stream().map(mapper::toDTO).toList();



    }

    @Override
    public Optional<Movie> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public MovieDTO save(MovieDTO data) {
        Movie entity = mapper.toEntity(data);
        log.info("Saving Movie to Repository");
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void update(long id, MovieDTO data) throws Exception {
        Optional<Movie> result = repository.findById(id);
        log.info("Searching {} in the Repository", id);


        if (result.isEmpty()) {
            log.warn("Id {} doesnt exist in the Repository", id);
            throw new Exception("No has visto esa pelicula");
        }

        Movie movie = result.get();

        movie.setName(data.getName());

        movie.setYear(data.getYear());
        log.info("Id {}found updating info",id);


        repository.save(movie);


    }

    @Override
    public void delete(long id) throws Exception {
        Optional<Movie> result = repository.findById(id);

        if (result.isEmpty()) {
            log.warn("Id {} doesnt exist in the Repository", id);
            throw new Exception("No has visto esa pelicula");
        }

        repository.deleteById(id);
        log.info("Id {} deleted ",id);

    }

    @Override
    public String getLink(String name)  {
        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=<Api Key>d8&language=en-US&query=" + name + "&page=1&include_adult=false");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            log.info("Connecting with url {} deleted ",url);

            // comprobar petición
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                log.warn("Error in the request");
                throw new RuntimeException("Error has ocurred " + responseCode);
            } else {
                //Abrir scaner que lea el flujo
                log.info("Openning the Scanner");
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());

                }

                scanner.close();

                //Parse objects
                log.info("Parsing object");
                JSONObject jsonObject = new JSONObject(informationString.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                log.info("Getting JSONObject");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                int id = jsonObject1.getInt("id");
                log.info("Returning link ");
                return "https://v2.vidsrc.me/embed/" + id +"/";



                //System.out.println(id);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.warn("Error in the request");
        return "error";

    }

    @Override
    public String getName(String name) {
        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=<Api Key>d8&language=en-US&query=" + name + "&page=1&include_adult=false");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            log.info("Connecting with url {} deleted ",url);

            // comprobar petición
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                log.warn("Error in the request");
                throw new RuntimeException("Error has ocurred " + responseCode);
            } else {
                //Abrir scaner que lea el flujo
                log.info("Openning the Scanner");
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());

                }

                scanner.close();

                //Parse objects
                log.info("Parsing object");
                JSONObject jsonObject = new JSONObject(informationString.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                log.info("Getting JSONObject");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                log.info("Returning original title ");
                String title = jsonObject1.getString("original_title");
                return title;

                //System.out.println(id);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.warn("Error in the request");
        return "error";


    }

    @Override
    public String getYear(String name) {
        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=<Api Key>d8&language=en-US&query=" + name + "&page=1&include_adult=false");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            log.info("Connecting with url {} deleted ",url);

            // comprobar petición
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                log.warn("Error in the request");
                throw new RuntimeException("Error has ocurred " + responseCode);
            } else {
                //Abrir scaner que lea el flujo
                log.info("Openning the Scanner");
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());

                }

                scanner.close();

                //Parse objects
                log.info("Parsing object");
                JSONObject jsonObject = new JSONObject(informationString.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                log.info("Getting JSONObject");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                log.info("Returning Release Date ");
                String title = jsonObject1.getString("release_date");
                return title;

                //System.out.println(id);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.warn("Error in the request");
        return "error";


    }

    @Override
    public List<String> findMovie(String name) {
        List<String> movie = new ArrayList<>();
        log.info("Retrieving {} info", name);
        movie.add("Original title: " + getName(name));
        movie.add("Release Date: " + getYear(name));
        movie.add("Link to vidsrc: " + getLink(name));
        log.info("Returning {} info", name);
        return movie;
    }

    @Override
    public void saveFromName(String name) {
        log.info("Retrieving {} info", name);
        Movie movie = new Movie();
        String strYear = getYear(name);
        String[] lstYear = (strYear.split("-",3));
        String years = lstYear[0];
        Long varLong=Long.parseLong(years);
        movie.setName(getName(name));
        movie.setYear(varLong);
        log.info("Returning {} info", name);
        log.info("Saving {} info", name);
        repository.save(movie);

    }



}
