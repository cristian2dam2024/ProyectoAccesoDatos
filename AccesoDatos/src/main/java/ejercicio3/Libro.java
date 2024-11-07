package ejercicio3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Libro {
	
	private String id;
	private String Author;
	private String Title;
	private String Genre;
	private Double Price;
	private LocalDate PublishDate;
	private String Description;
    
	
	
    
    public Libro(Node nodo) {
    	
    	this.id = nodo.getAttributes().getNamedItem("id").getTextContent();
    	
    	HashMap<String, String> datos = new HashMap<>();
    	addElement(nodo.getChildNodes(), datos);
    	setNumbers(datos);
    	
    	this.Author = datos.get("Author");
    	this.Title = datos.get("Title");
    	this.Genre = datos.get("Genre");
    	this.Price = Double.valueOf(datos.get("Price"));
    	this.PublishDate = getDate(datos.get("PublishDate"));
    	this.Description = datos.get("Description");
    	
	}

	private LocalDate getDate(String registroFecha) {
		return LocalDate.parse(registroFecha);
	}

    
    private void setNumbers(HashMap<String, String> datos) {
		// TODO Auto-generated method stub
    	String [] claveNumeros = {"Price"};

    	for (int i = 0; i < claveNumeros.length; i++) {
    		String contenido = datos.get(claveNumeros[i]);
    		if(contenido.isEmpty() || contenido == null) {
    			//System.out.println("La clave es nula");
    			datos.replace(claveNumeros[i], "0");
    		}
		}
	}

	private void addElement(NodeList elementos, HashMap<String, String> datos) {
    	
		for (int i = 0; i < elementos.getLength(); i++) {

			Node etiqueta = elementos.item(i);
			datos.put(etiqueta.getNodeName(), etiqueta.getTextContent());
			
			NodeList hijos = etiqueta.getChildNodes();
			if (hijos.getLength() != 0) {
				addElement(hijos, datos);
			}
		}
    }

	@Override
	public String toString() {
		return "Libro [id=" + id + ", Author=" + Author + ", Title=" + Title + ", Genre=" + Genre + ", Price=" + Price
				+ ", PublishDate=" + PublishDate + ", Description=" + Description + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public LocalDate getPublishDate() {
		return PublishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		PublishDate = publishDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
}
