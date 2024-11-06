package com.example.filmoteca_lucascandela;

import java.util.ArrayList;

public class FilmDataSource {
    public static ArrayList<Film> films;

    public static void Initialize() {
        films = new ArrayList<Film>();

        Film f = new Film(R.drawable.interstellar,"Interstellar","Christopher Nolan",1984,
                Film.GENRE_SCIFI, Film.FORMAT_DIGITAL,"http://www.imdb.com/title/tt0816692",
                "A team of explorers travel throught a wormhole in space in an attempt to ensure humanity’s survival");
        films.add(f);

        f = new Film(R.drawable.backtothefuture, "Back to the future", "Robert Zemeckis",1985,
                Film.GENRE_SCIFI, Film.FORMAT_DIGITAL,"http://www.imdb.com/title/tt0088763",
                "Marty McFly, is sent 30 years into the past in a time-travelling DeLorean."  );

        films.add(f);

        f = new Film(R.drawable.gladiator, "Gladiator", "Ridley Scott", 2000,
                Film.GENRE_ACTION, Film.FORMAT_BLURAY, "https://www.imdb.com/title/tt0172495/?ref_=chtmvm_t_3",
                "Un exgeneral romano se propone vengarse del emperador corrupto que asesinó a su familia y lo envió a la esclavitud");

        films.add(f);

        f = new Film(R.drawable.cadenaperpetua, "Cadena Perpetua", "Frank Darabont", 1994,
                Film.GENRE_DRAMA, Film.FORMAT_DVD, "https://www.imdb.com/title/tt0111161/?ref_=chtmvm_t_1",
                "Andy Dufresne es encarcelado por matar a su esposa y al amante de esta. " +
                        "Tras una dura adaptación, intenta mejorar las condiciones de la prisión y dar esperanza a sus compañeros.");

        films.add(f);

        f = new Film(R.drawable.elresplandor, "El Resplandor", "Stanley Kubrick", 1980,
                Film.GENRE_DRAMA, Film.FORMAT_DIGITAL, "https://www.imdb.com/title/tt0081505/?ref_=chtmvm_t_5",
                "Una familia se dirige a un hotel aislado para pasar el invierno. " +
                        "Allí, una presencia espiritual maligna violenta al padre, mientras el hijo, psíquico, tiene horripilantes visiones del pasado y del futuro.");

        films.add(f);

        f = new Film(R.drawable.robotsalvaje, "Robot salvaje", "Chris Sanders", 2024,
                Film.GENRE_SCIFI, Film.FORMAT_DIGITAL, "https://www.imdb.com/title/tt29623480/?ref_=chtmvm_t_7",
                "ALa robot Roz, que ha naufragado en una isla deshabitada, debe aprender a adaptarse al duro entorno, " +
                        "forjando poco a poco relaciones con la fauna local y convirtiéndose en madre adoptiva de una cría de ganso huérfana.");

        films.add(f);

        f = new Film(R.drawable.pesadillaantesdenavidad, "Pesadilla antes de Navidad", "Tim Burton", 1993,
                Film.GENRE_HORROR, Film.FORMAT_BLURAY, "https://www.imdb.com/title/tt0107688/?ref_=chtmvm_t_12",
                "Jack Skellington, el rey de la Ciudad de Halloween, descubre la Ciudad de la Navidad, " +
                        "pero sus intentos de llevar la Navidad a Halloween causan confusión.");

        films.add(f);

        f = new Film(R.drawable.cazafantasmas, "Los Cazafantasmas", "Ivan Reitman", 1984,
                Film.GENRE_COMEDY, Film.FORMAT_DVD, "https://www.imdb.com/title/tt0087332/?ref_=chtmvm_t_16",
                "Tres parapsicólogos que se ven obligados a abandonar su financiación universitaria " +
                        "montan un singular servicio de eliminación de fantasmas en la ciudad de Nueva York, " +
                        "atrayendo a clientes asustados pero escépticos.");

        films.add(f);


        f = new Film(R.drawable.delreves2, "Del revés 2", "Kelsey Mann", 2024,
                Film.GENRE_COMEDY, Film.FORMAT_DIGITAL, "https://www.imdb.com/title/tt22022452/?ref_=chtmvm_t_20",
                "Riley, en su adolescencia, al encuentro de nuevas emociones.");

        films.add(f);

        f = new Film(R.drawable.saturdaynight, "Saturday Night", "Jason Reitman", 2024,
                Film.GENRE_DRAMA, Film.FORMAT_DVD, "https://www.imdb.com/title/tt27657135/?ref_=chtmvm_t_29",
                "El 11 de octubre de 1975, un grupo de jóvenes cómicos cambió la televisión para siempre. " +
                        "Siga la historia entre bastidores en los momentos previos a la primera emisión de SNL.");

        films.add(f);

        f = new Film(R.drawable.scream, "Scream: Vigila quién llama", "Wes Craven", 1996,
                Film.GENRE_HORROR, Film.FORMAT_BLURAY, "https://www.imdb.com/title/tt0117571/?ref_=chtmvm_t_24",
                "Un año después del asesinato de su madre, una adolescente es aterrorizada por un nuevo asesino, " +
                        "que tiene como objetivo a la chica y a sus amigos utilizando las películas de terror como parte de un juego mortal.");

        films.add(f);

        f = new Film(R.drawable.deadpoollobezno, "Deadpool y Lobezno", "Shawn Levy", 2024,
                Film.GENRE_ACTION, Film.FORMAT_DIGITAL, "https://www.imdb.com/title/tt6263850/?ref_=chtmvm_t_24",
                "La Autoridad de Variación Temporal (AVT) ofrece a Deadpool un lugar en el Universo cinematográfico de Marvel, " +
                        "pero en su lugar recluta a una variante de Lobezno para salvar su universo de la extinción.");

        films.add(f);

        f = new Film(R.drawable.transformersone, "Transformers One", "Josh Cooley", 2024,
                Film.GENRE_SCIFI, Film.FORMAT_DVD, "https://www.imdb.com/title/tt8864596/?ref_=chtmvm_t_33",
                "Una historia ambientada en Cybertron, hogar tanto de los Autobots como de los Decepticons. " +
                        "La película se centrará en la relación entre Optimus Prime y Megatron.");

        films.add(f);

        f = new Film(R.drawable.wicked, "Wicked", "Jon M.Chu", 2024,
                Film.GENRE_SCIFI, Film.FORMAT_DIGITAL, "https://www.imdb.com/title/tt1262426/?ref_=chtmvm_t_35",
                "La historia de cómo una joven de piel verde es incriminada por el Mago de Oz y se convierte en la Malvada Bruja del Oeste. " +
                        "La primera de una adaptación cinematográfica en dos partes del musical de Broadway.");

        films.add(f);

        f = new Film(R.drawable.maxxxine, "MaXXXine", "Ti West", 2024,
                Film.GENRE_HORROR, Film.FORMAT_BLURAY, "https://www.imdb.com/title/tt22048412/?ref_=chtmvm_t_36",
                "En el Hollywood de los ochenta, la estrella de cine para adultos y aspirante a actriz Maxine Minx finalmente " +
                        "consigue su gran oportunidad. Un misterioso asesino acecha a las estrellas, sangre amenaza con revelar su siniestro pasado.");

        films.add(f);
    }
}
