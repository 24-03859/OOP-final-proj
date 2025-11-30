abstract class Dilemma {
    private String category;
    private String dilemma;
    private String solution;
    private String status;
    private String author;

    // Getters and setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDilemma() { return dilemma; }
    public void setDilemma(String dilemma) { this.dilemma = dilemma; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    // Abstract method for polymorphism
    public abstract void showInfo();
}

class ConcreteDilemma extends Dilemma {
    @Override
    public void showInfo() {
        System.out.println("   DILEMMA: " + getDilemma());
        System.out.println("   CATEGORY: " + getCategory());
        System.out.println("   SOLUTION: " + getSolution());
        System.out.println("   STATUS: " + getStatus());
        System.out.println("   AUTHOR: " + getAuthor());
    }
}
