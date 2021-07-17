package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	Map<Integer,Artist> artistIdMap;
	Graph<Artist,DefaultWeightedEdge> grafo;
	List<EdgeAndWeight> edgeAndWeight;
	
	public String doCreaGrafo(String role) {
		ArtsmiaDAO dao = new ArtsmiaDAO();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		artistIdMap = new HashMap<>();
		for(Artist a: dao.getVertex(role)) 
			artistIdMap.put(a.getArtist_id(), a);
		Graphs.addAllVertices(grafo, artistIdMap.values());
		edgeAndWeight = dao.getArchi(role);
		for(EdgeAndWeight e: edgeAndWeight) {
			Graphs.addEdge(grafo, artistIdMap.get(e.getArtist_id1()), artistIdMap.get(e.getArtist_id2()), e.getPeso());
		}
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n";
		return result;
	}
	
	public String doArtistiConnessi() {
		if(this.grafo==null)
			return "Crea prima il grafo!!!\n\n";
		String res = "\nElenco delle coppie di artisti ed il numero delle esposizioni comuni: \n";
		Collections.sort(edgeAndWeight);
		for(EdgeAndWeight e: edgeAndWeight) {
			res += artistIdMap.get(e.getArtist_id1())+" - "+artistIdMap.get(e.getArtist_id2())+" | "+e.getPeso()+"\n";
		}
		return res;
	}
	
	
	public List<String> getAllRole(){
		ArtsmiaDAO dao = new ArtsmiaDAO();
		List<String> result = new ArrayList<>(dao.getAllRole());
		return result;
	}
}
