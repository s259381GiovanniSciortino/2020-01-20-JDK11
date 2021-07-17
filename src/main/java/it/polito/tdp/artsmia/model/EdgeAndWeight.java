package it.polito.tdp.artsmia.model;

public class EdgeAndWeight implements Comparable<EdgeAndWeight>{
	int artist_id1;
	int artist_id2;
	Integer peso;
	public EdgeAndWeight(int artist_id1, int artist_id2, Integer peso) {
		super();
		this.artist_id1 = artist_id1;
		this.artist_id2 = artist_id2;
		this.peso = peso;
	}
	public int getArtist_id1() {
		return artist_id1;
	}
	public void setArtist_id1(int artist_id1) {
		this.artist_id1 = artist_id1;
	}
	public int getArtist_id2() {
		return artist_id2;
	}
	public void setArtist_id2(int artist_id2) {
		this.artist_id2 = artist_id2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(EdgeAndWeight other) {
		return -this.getPeso().compareTo(other.getPeso());
	}
	
}
