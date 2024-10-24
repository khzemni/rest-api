package com.hosting.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hosting.restapi.entity.Criteria;
import com.hosting.restapi.entity.CriteriaCategory;
import com.hosting.restapi.entity.LodgingType;
import com.hosting.restapi.repository.CriteriaCategoryRepository;
import com.hosting.restapi.repository.CriteriaRepository;
import com.hosting.restapi.repository.LodgingTypeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class DataInitializer {

//implements CommandLineRunner {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private LodgingTypeRepository lodgingTypeRepository; // Create repositories for each entity if not exists
//    @Autowired
//    private CriteriaCategoryRepository criteriaCategoryRepository;
//    @Autowired
//    private CriteriaRepository criteriaRepository;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//    	// Create objects for LodgingType entities
//        List<LodgingType> lodgingTypes = createLodgingTypes();
//        lodgingTypeRepository.saveAllAndFlush(lodgingTypes);
//
//        // Create objects for CriteriaCategory entities
//        List<CriteriaCategory> criteriaCategories = createCriteriaCategories();
//        criteriaCategoryRepository.saveAllAndFlush(criteriaCategories);
//
//        // Create objects for Criteria entities
//        List<Criteria> criterias = createCriterias();
//        criteriaRepository.saveAllAndFlush(criterias);
//    }
//    
//    private List<LodgingType> createLodgingTypes() {
//        List<LodgingType> lodgingTypes = new ArrayList<>();
//        lodgingTypes.add(new LodgingType(3L, "Barn house"));
//        lodgingTypes.add(new LodgingType(4L, "Ranch"));
//        lodgingTypes.add(new LodgingType(5L, "Chambre d'hôtes"));
//        lodgingTypes.add(new LodgingType(6L, "Bateau"));
//        lodgingTypes.add(new LodgingType(7L, "Cabane"));
//        lodgingTypes.add(new LodgingType(8L, "Château"));
//        lodgingTypes.add(new LodgingType(9L, "RV/Camping car"));
//        lodgingTypes.add(new LodgingType(10L, "Conteneur"));
//        lodgingTypes.add(new LodgingType(11L, "Hotel"));
//        lodgingTypes.add(new LodgingType(12L, "Maison asiatique"));
//        lodgingTypes.add(new LodgingType(13L, "Farm house"));
//        lodgingTypes.add(new LodgingType(14L, "Maison d'hôte"));
//        lodgingTypes.add(new LodgingType(15L, "Logement sur l'eau"));
//        lodgingTypes.add(new LodgingType(16L, "Riadh"));
//        lodgingTypes.add(new LodgingType(17L, "Tiny house"));
//        lodgingTypes.add(new LodgingType(18L, "Tour"));
//        lodgingTypes.add(new LodgingType(19L, "Cabane perchée"));
//        lodgingTypes.add(new LodgingType(20L, "Trullo"));
//        lodgingTypes.add(new LodgingType(21L, "Country house"));
//        lodgingTypes.add(new LodgingType(23L, "Bungalow"));
//        return lodgingTypes;
//    }
//    
//    private List<CriteriaCategory> createCriteriaCategories() {
//        List<CriteriaCategory> criteriaCategories = new ArrayList<>();
//        criteriaCategories.add(new CriteriaCategory(1L, "Vue et Emplacement"));
//        criteriaCategories.add(new CriteriaCategory(2L, "Parking et installations"));
//        criteriaCategories.add(new CriteriaCategory(3L, "Chauffage et Climatisation"));
//        criteriaCategories.add(new CriteriaCategory(4L, "Internet et Bureau"));
//        criteriaCategories.add(new CriteriaCategory(5L, "Salle de bain"));
//        criteriaCategories.add(new CriteriaCategory(6L, "Cuisine et salle à manger"));
//        criteriaCategories.add(new CriteriaCategory(7L, "Chambre et linge"));
//        criteriaCategories.add(new CriteriaCategory(8L, "Divertissement"));
//        criteriaCategories.add(new CriteriaCategory(9L, "Extérieur"));
//        criteriaCategories.add(new CriteriaCategory(10L, "Services"));
//        criteriaCategories.add(new CriteriaCategory(11L, "Sécurite"));
//        return criteriaCategories;
//    }
//    
//    private List<Criteria> createCriterias() {
//        List<Criteria> criterias = new ArrayList<>();
//        criterias.add(new Criteria(3L, "Vue sur la ville", criteriaCategoryRepository.findById(1L).get()));
//        criterias.add(new Criteria(4L, "Vue sur la plage", criteriaCategoryRepository.findById(1L).get()));
//        criterias.add(new Criteria(5L, "Compagne", criteriaCategoryRepository.findById(1L).get()));
//        criterias.add(new Criteria(6L, "Ville", criteriaCategoryRepository.findById(1L).get()));
//        criterias.add(new Criteria(7L, "Bois", criteriaCategoryRepository.findById(1L).get()));
//        criterias.add(new Criteria(8L, "Parking Gratuit sur place", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(9L, "Parking Gratuit Externe", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(10L, "Parking Payant", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(11L, "Accès privée", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(12L, "Asscenseur", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(13L, "Piscine Extérieur", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(14L, "Piscine Intérieur", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(15L, "Salle de sport", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(16L, "Sauna", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(17L, "Jacuzzi", criteriaCategoryRepository.findById(2L).get()));
//        criterias.add(new Criteria(18L, "Climatisation Centralisé", criteriaCategoryRepository.findById(3L).get()));
//        criterias.add(new Criteria(19L, "Chauffage Centralisé", criteriaCategoryRepository.findById(3L).get()));
//        criterias.add(new Criteria(20L, "Climatisation", criteriaCategoryRepository.findById(3L).get()));
//        criterias.add(new Criteria(21L, "Chauffage", criteriaCategoryRepository.findById(3L).get()));
//        criterias.add(new Criteria(22L, "Internet filaire", criteriaCategoryRepository.findById(4L).get()));
//        criterias.add(new Criteria(23L, "wifi", criteriaCategoryRepository.findById(4L).get()));
//        criterias.add(new Criteria(24L, "Espace de travail dédié", criteriaCategoryRepository.findById(4L).get()));
//        criterias.add(new Criteria(25L, "Bureau et chaise de travail", criteriaCategoryRepository.findById(4L).get()));
//        criterias.add(new Criteria(26L, "Sèche-cheveux", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(27L, "Bidet", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(28L, "Eau chaude", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(29L, "Baignoire", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(30L, "Douche", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(31L, "Produit de nettoyage", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(32L, "Gel douche & Shampooing", criteriaCategoryRepository.findById(5L).get()));
//        criterias.add(new Criteria(33L, "Espace de cuisine", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(34L, "Réfrigirateur", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(35L, "Four à micro-ondes", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(36L, "Equipement de cuisine de base", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(37L, "Vaisselle et couverts", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(38L, "Cuisinère a Gaz", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(39L, "Four", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(40L, "Congélateur", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(41L, "Cafétière : Nespresso", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(42L, "Plaque de cuisson", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(43L, "Table à Manger", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(44L, "Cafétière filtre", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(45L, "Verre à vin", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(46L, "Grille pain", criteriaCategoryRepository.findById(6L).get()));
//        criterias.add(new Criteria(47L, "Sèche-linge", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(48L, "Lave-linge", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(49L, "Serviette, draps, savon, papier toilette (Equipements de base)", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(50L, "Cintres", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(51L, "Draps (linge de lit en cotton)", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(52L, "Oreillers et couvertures supplémentaires", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(53L, "Stores", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(54L, "Fer à repasser", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(55L, "Coffre fort", criteriaCategoryRepository.findById(7L).get()));
//        criterias.add(new Criteria(56L, "TV", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(57L, "Home-Cinéma", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(58L, "Système sonor", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(59L, "Console de jeux", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(60L, "Livres", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(61L, "Jeux sociales & jouets", criteriaCategoryRepository.findById(8L).get()));
//        criterias.add(new Criteria(62L, "Mobilier d'extérieur", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(63L, "Espace repas en plein aire", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(64L, "Cuisine extérieur", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(65L, "Chaise longue", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(66L, "Barbecue", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(67L, "Patio ou Balcon", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(68L, "Jardin", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(69L, "Balanceoire", criteriaCategoryRepository.findById(9L).get()));
//        criterias.add(new Criteria(70L, "Animaux acceptés", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(71L, "Dépôt de bagage autorisé (arrivé anticipé départ tardif)", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(72L, "Clés remises par l'hote", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(73L, "Séjour de longue durée", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(74L, "Arrivée autonome", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(75L, "Logement fumeur", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(76L, "Boite à clés sécurisé", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(77L, "Ménage disponible pendant le séjour", criteriaCategoryRepository.findById(10L).get()));
//        criterias.add(new Criteria(78L, "Kit de premiers secours", criteriaCategoryRepository.findById(11L).get()));
//        criterias.add(new Criteria(79L, "Extincteur", criteriaCategoryRepository.findById(11L).get()));
//        criterias.add(new Criteria(80L, "Caméra de surveillance extérieur et/ou dans les espace communs", criteriaCategoryRepository.findById(11L).get()));
//        criterias.add(new Criteria(81L, "Détécteur de monoxyde de carbone", criteriaCategoryRepository.findById(11L).get()));
//
//        return criterias;
//    }

}
