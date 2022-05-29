package com.revature.phuflix.services;

import com.revature.phuflix.daos.PhuboxDAO;
import com.revature.phuflix.models.Phubox;
import com.revature.phuflix.util.custom_exception.UserInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhuboxService {
    private final PhuboxDAO phuboxDAO;

    public PhuboxService(PhuboxDAO phuboxDAO){
        this.phuboxDAO = phuboxDAO;
    }

    public boolean isValidST(String state){
    String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};

    for (String s: states){
        if (state.equals(s)){
            return true;
        }
    }
    throw new UserInputException("Not valid State Abbreviation");
    }

    public void newBox(Phubox phubox){
        phuboxDAO.save(phubox);
    }

    public boolean isValidStreetName(String input){
        if(input.matches("\\b[A-Z][a-z]+((?= [A-Z]) ([A-Z]\\w+){0,})*|\\d[a-z]+")) return true;
        throw new UserInputException("Not valid street name. Example (North Hill, 1st)");
    }

    public boolean isValidCity(String input){
        if(input.matches("\\b[A-Z][a-z]+((?= [A-Z]) ([A-Z]\\w+){0,})*")) return true;
        throw new UserInputException("Not valid city name. Example (New Haven, Jacksonville)");
    }

    public boolean isValidStreetType(String streetType) {
        //String[] street = {"Alley","Anex","Arcade","Avenue","Bayou","Beach","Bend","Bluff","Bluffs","Bottom","Boulevard","Branch","Bridge","Brook","Brooks","Burg","Burgs","Bypass","Camp","Canyon","Cape","Causeway","Center","Centers","Circle","Circles","Cliff","Cliffs","Club","Common","Commons","Corner","Corners","Course","Court","Courts","Cove","Coves","Creek","Crescent","Crest","Crossing","Crossroad","Crossroads","Curve","Dale","Dam","Divide","Drive","Drives","Estat","Estates","Expressway","Extension","Extension","Fall","Falls","Ferry","Field","Fields","Flat","Flats","Ford","Fords","Forest","Forge","Forges","Fork","Forks","Fort","Freeway","Garden","Gardens","Gateway","Glen","Glens","Green","Greens","Grove","Groves","Harbor","Harbors""","Haven""","Heights""","Highway""","Hill""","Hills""","Hollow""","Inlet""","Island""","Islands""","Isle""","Junction""","Junctions""","Key""","Keys""","Knoll""","Knolls""","Lake""","Lakes""","Land""","Landing""","Lane""","Light""","Lights""","Loaf""","Lock""","Locks""","Lodge""","Loop""","Mall""","Manor""","Manors""","Meadow""","Meadows""","Mews""","Mill""","Mills""","Mission""","Motorway""","Mount""","Mountain""","Mountains""","Neck""","Orchard""","Oval""","Overpass""","Park""","Parks""","Parkway""","Parkways""","Pass""","Passage""","Path""","Pike""","Pine""","Pines""","Place""","Plain""","Plains""","Plaza""","Point""","Points""","Port""","Ports""","Prairie""","Radial""","Ramp""","Ranch""","Rapid""","Rapids""","Rest""","Ridge""","Ridges""","River""","Road""","Roads""","Route""","Row""","Rue""","Run""","Shoal""","Shoals""","Shore""","Shores""","Skyway""","Spring""","Springs""","Spur""","Spurs""","Square""","Squares""","Station""","Stravenue""","Stream""","Street""","Streets""","Summit""","Terrace""","Throughway""","Trace""","Track""","Trafficway""","Trail""","Trailer""","Tunnel""","Turnpike""","Underpass""","Union""","Unions""","Valley""","Valleys""","Viaduct""","View""","Views""","Village""","Villages""","Ville""","Vista""","Walk""","Walks""","Wall","Way","Ways","Well","Wells"}
        String street ="Alley,Anex,Arcade,Avenue,Bayou,Beach,Bend,Bluff,Bluffs,Bottom,Boulevard,Branch,Bridge,Brook,Brooks,Burg,Burgs,Bypass,Camp,Canyon,Cape,Causeway,Center,Centers,Circle,Circles,Cliff,Cliffs,Club,Common,Commons,Corner,Corners,Course,Court,Courts,Cove,Coves,Creek,Crescent,Crest,Crossing,Crossroad,Crossroads,Curve,Dale,Dam,Divide,Drive,Drives,Estate,Estates,Expressway,Extension,Extensions,Fall,Falls,Ferry,Field,Fields,Flat,Flats,Ford,Fords,Forest,Forge,Forges,Fork,Forks,Fort,Freeway,Garden,Gardens,Gateway,Glen,Glens,Green,Greens,Grove,Groves,Harbor,Harbors,Haven,Heights,Highway,Hill,Hills,Hollow,Inlet,Island,Islands,Isle,Junction,Junctions,Key,Keys,Knoll,Knolls,Lake,Lakes,Land,Landing,Lane,Light,Lights,Loaf,Lock,Locks,Lodge,Loop,Mall,Manor,Manors,Meadow,Meadows,Mews,Mill,Mills,Mission,Motorway,Mount,Mountain,Mountains,Neck,Orchard,Oval,Overpass,Park,Parks,Parkway,Parkways,Pass,Passage,Path,Pike,Pine,Pines,Place,Plain,Plains,Plaza,Point,Points,Port,Ports,Prairie,Radial,Ramp,Ranch,Rapid,Rapids,Rest,Ridge,Ridges,River,Road,Roads,Route,Row,Rue,Run,Shoal,Shoals,Shore,Shores,Skyway,Spring,Springs,Spur,Spurs,Square,Squares,Station,Stravenue,Stream,Street,Streets,Summit,Terrace,Throughway,Trace,Track,Trafficway,Trail,Trailer,Tunnel,Turnpike,Underpass,Union,Unions,Valley,Valleys,Viaduct,View,Views,Village,Villages,Ville,Vista,Walk,Walks,Wall,Way,Ways,Well,Wells";
        String[] x = street.split(",");
        for (String s: x){
            if (streetType.equals(s)){
                return true;
            }
        }
        throw new UserInputException("Invalid Street Type");
    }

    public boolean isValidStreetNumber(String input){
        if(input.matches("^\\d*")){
            return true;
        }
        throw new UserInputException("Please enter a number");
    }

    public List<Phubox> getAllPhuBoxes(){
        return phuboxDAO.getAll();

    }

    public Phubox getPhuboxByID(String id){
        return phuboxDAO.getById(id);
    }

    public boolean isValidSelect(String input) {
        if(input.matches("[1-3]")){
            return true;
        }
        throw new UserInputException("Please enter a correct input. Back to Main Menu");
    }

    public boolean isValidPhubox(Phubox box) {
        if(box.getId()==null){throw new UserInputException("Invalid input");}
        return true;
    }
}
