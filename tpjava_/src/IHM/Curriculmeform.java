/*
package IHM;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Curriculmeform extends JFrame {
    JButton btnquitter, btnval, btnImage;
    JLabel labeltite, lb_nom, lb_prenom, lb_pseudo, lb_image, lb_dateNaissance, lb_gouvernorat, lb_gender, lb_skills, lb_hobbies, lb_languages;
    JTextField tf_nom, tf_prenom, tf_pseudo, tf_skills, tf_hobbies, tf_languages;
    JComboBox<String> cb_jour, cb_mois, cb_annee, cb_gouvernorat;
    JFileChooser fileChooser;
    File selectedFile;
    JCheckBox cb_male, cb_female;

    Curriculmeform() {
        this.setTitle("Curriculum Vitae");
        this.setSize(800, 900); // Increased width and height
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        labeltite = new JLabel("Curriculum Vitae", SwingConstants.CENTER);
        labeltite.setForeground(Color.WHITE);
        labeltite.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        labeltite.setOpaque(true);
        labeltite.setBackground(new Color(85, 107, 47)); // Olive Green Background
        labeltite.setPreferredSize(new Dimension(600, 50));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(labeltite, gbc);

        // Input Fields Setup
        lb_nom = new JLabel("Nom: ");
        tf_nom = new JTextField(15);
        lb_prenom = new JLabel("Prénom: ");
        tf_prenom = new JTextField(15);
        lb_pseudo = new JLabel("Pseudo: ");
        tf_pseudo = new JTextField(15);

        addComponent(lb_nom, 0, 1, gbc);
        addComponent(tf_nom, 1, 1, gbc);
        addComponent(lb_prenom, 0, 2, gbc);
        addComponent(tf_prenom, 1, 2, gbc);
        addComponent(lb_pseudo, 0, 3, gbc);
        addComponent(tf_pseudo, 1, 3, gbc);

        // Date of Birth ComboBoxes
        lb_dateNaissance = new JLabel("Date de naissance: ");
        String[] jours = new String[31];
        for (int i = 0; i < 31; i++) jours[i] = String.valueOf(i + 1);
        cb_jour = new JComboBox<>(jours);
        String[] moisOptions = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        cb_mois = new JComboBox<>(moisOptions);
        String[] annees = new String[100];
        for (int i = 0; i < 100; i++) annees[i] = String.valueOf(2024 - i);
        cb_annee = new JComboBox<>(annees);
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(cb_jour);
        datePanel.add(cb_mois);
        datePanel.add(cb_annee);

        addComponent(lb_dateNaissance, 0, 4, gbc);
        addComponent(datePanel, 1, 4, gbc);

        // Governorate ComboBox
        lb_gouvernorat = new JLabel("Gouvernorat: ");
        String[] gouvernorats = {"Tunis", "Ariana", "Ben Arous", "Manouba", "Nabeul", "Zaghouan", "Bizerte", "Beja", "Jendouba", "Kef", "Siliana", "Sousse", "Monastir", "Mahdia", "Sfax", "Kairouan", "Kasserine", "Sidi Bouzid", "Gabes", "Medenine", "Tataouine", "Gafsa", "Tozeur", "Kebili"};
        cb_gouvernorat = new JComboBox<>(gouvernorats);
        addComponent(lb_gouvernorat, 0, 5, gbc);
        addComponent(cb_gouvernorat, 1, 5, gbc);

        // Image File Selection
        lb_image = new JLabel("Image: Aucun fichier sélectionné");
        btnImage = new JButton("Sélectionner une image");
        addComponent(lb_image, 0, 6, gbc);
        addComponent(btnImage, 1, 6, gbc);

        // Gender Selection
        lb_gender = new JLabel("Genre: ");
        cb_male = new JCheckBox("Homme");
        cb_female = new JCheckBox("Femme");

        JPanel genderPanel = new JPanel();
        genderPanel.add(cb_male);
        genderPanel.add(cb_female);

        addComponent(lb_gender, 0, 7, gbc);
        addComponent(genderPanel, 1, 7, gbc);

        // Skills
        lb_skills = new JLabel("Compétences: ");
        tf_skills = new JTextField(20);
        addComponent(lb_skills, 0, 8, gbc);
        addComponent(tf_skills, 1, 8, gbc);

        // Hobbies
        lb_hobbies = new JLabel("Loisirs: ");
        tf_hobbies = new JTextField(20);
        addComponent(lb_hobbies, 0, 9, gbc);
        addComponent(tf_hobbies, 1, 9, gbc);

        // Languages
        lb_languages = new JLabel("Langues: ");
        tf_languages = new JTextField(20);
        addComponent(lb_languages, 0, 10, gbc);
        addComponent(tf_languages, 1, 10, gbc);

        // Buttons
        btnval = new JButton("Valider");
        btnquitter = new JButton("Quitter");
        addComponent(btnval, 0, 11, gbc);
        addComponent(btnquitter, 1, 11, gbc);

        // Image Selection Action
        btnImage.addActionListener(e -> {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                lb_image.setText("Image: " + selectedFile.getName());
            }
        });

        // Exit Button Action
        btnquitter.addActionListener(e -> System.exit(0));

        // Validate Button Action (HTML CV Generation)
        btnval.addActionListener(e -> {
            String nom = tf_nom.getText();
            String prenom = tf_prenom.getText();
            String pseudo = tf_pseudo.getText();
            String jour = (String) cb_jour.getSelectedItem();
            String mois = (String) cb_mois.getSelectedItem();
            String annee = (String) cb_annee.getSelectedItem();
            String gouvernorat = (String) cb_gouvernorat.getSelectedItem();
            String gender = cb_male.isSelected() ? "Homme" : (cb_female.isSelected() ? "Femme" : "Non précisé");
            String skills = tf_skills.getText();
            String hobbies = tf_hobbies.getText();
            String languages = tf_languages.getText();

            // Validate Fields
            if (nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty() || skills.isEmpty() || hobbies.isEmpty() || languages.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Write to HTML File
            File f = new File("cv.html");
            try (FileWriter fw = new FileWriter(f, false)) {
                fw.write("<html><head><title>CV</title><style>body{font-family:Arial, sans-serif; background-color:#f4f4f9; margin:0; padding:0;} h1{color:#4CAF50;} h2, h3{color:#333;} .container{width:80%; margin:auto;} img{border-radius:10px; max-width:200px; height:auto;} .details{margin-top:10px; font-size:16px;} .gender{margin-top:5px;} .section-title{font-weight:bold; margin-top:20px;}</style></head><body>" +
                        "<div class='container'>" +
                        "<h1>" + nom + " " + prenom + "</h1>" +
                        "<h2>Pseudo: " + pseudo + "</h2>" +
                        "<h3>Date de naissance: " + jour + " " + mois + " " + annee + "</h3>" +
                        "<div class='details'><h3>Gouvernorat: " + gouvernorat + "</h3>" +
                        "<h3>Genre: " + gender + "</h3></div>" +
                        (selectedFile != null ? "<div class='image'><img src='" + selectedFile.toURI() + "' alt='Photo de profil'></div>" : "") +
                        "<div class='section-title'>Compétences:</div>" +
                        "<p>" + skills + "</p>" +
                        "<div class='section-title'>Loisirs:</div>" +
                        "<p>" + hobbies + "</p>" +
                        "<div class='section-title'>Langues:</div>" +
                        "<p>" + languages + "</p>" +
                        "</div></body></html>");
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addComponent(Component component, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }
}
*/



package IHM;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Curriculmeform extends JFrame {
    JButton btnquitter, btnval, btnImage;
    JLabel labeltite,  lb_nom, lb_prenom, lb_pseudo, lb_image, lb_dateNaissance, lb_gouvernorat, lb_gender, lb_skills, lb_hobbies, lb_languages, lb_adresse, lb_tel, lb_mail, lb_formations, lb_experiences;
    JTextField tf_nom,                             tf_prenom, tf_pseudo, tf_skills, tf_hobbies, tf_languages, tf_adresse, tf_tel, tf_mail;
    JComboBox<String> cb_jour, cb_mois, cb_annee, cb_gouvernorat;
    JTextArea ta_formations, ta_experiences;
    JFileChooser fileChooser;
    File selectedFile;
    JCheckBox cb_male, cb_female;


    Curriculmeform() {
        // Set JFrame properties
        this.setTitle("Curriculum Vitae");
        this.setSize(800, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);  // Padding for components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        labeltite = new JLabel("Curriculum Vitae", SwingConstants.CENTER);
        labeltite.setFont(new Font("Arial", Font.BOLD, 36));  // Larger and more professional font
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        this.add(labeltite, gbc);

        // Input Fields
        lb_nom = new JLabel("Nom: "); tf_nom = new JTextField(15);
        lb_prenom = new JLabel("Prénom: "); tf_prenom = new JTextField(15);
        lb_pseudo = new JLabel("Pseudo: "); tf_pseudo = new JTextField(15);
        lb_adresse = new JLabel("Adresse: "); tf_adresse = new JTextField(20);
        lb_tel = new JLabel("Téléphone: "); tf_tel = new JTextField(15);
        lb_mail = new JLabel("Email: "); tf_mail = new JTextField(20);
        lb_languages = new JLabel("Langages: "); tf_languages = new JTextField(20);
        lb_skills = new JLabel("Compétences: "); tf_skills = new JTextField(20);
        lb_hobbies = new JLabel("Hobbies: "); tf_hobbies = new JTextField(20);

        // Set larger fonts for labels and text fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        lb_nom.setFont(fieldFont); tf_nom.setFont(fieldFont);
        lb_prenom.setFont(fieldFont); tf_prenom.setFont(fieldFont);
        lb_pseudo.setFont(fieldFont); tf_pseudo.setFont(fieldFont);
        lb_adresse.setFont(fieldFont); tf_adresse.setFont(fieldFont);
        lb_tel.setFont(fieldFont); tf_tel.setFont(fieldFont);
        lb_mail.setFont(fieldFont); tf_mail.setFont(fieldFont);
        lb_languages.setFont(fieldFont); tf_languages.setFont(fieldFont);
        lb_skills.setFont(fieldFont); tf_skills.setFont(fieldFont);
        lb_hobbies.setFont(fieldFont); tf_hobbies.setFont(fieldFont);

        // Add components to layout
        addComponent(lb_nom, 0, 1, gbc); addComponent(tf_nom, 1, 1, gbc);
        addComponent(lb_prenom, 0, 2, gbc); addComponent(tf_prenom, 1, 2, gbc);
        addComponent(lb_pseudo, 0, 3, gbc); addComponent(tf_pseudo, 1, 3, gbc);
        addComponent(lb_adresse, 0, 4, gbc); addComponent(tf_adresse, 1, 4, gbc);
        addComponent(lb_tel, 0, 5, gbc); addComponent(tf_tel, 1, 5, gbc);
        addComponent(lb_mail, 0, 6, gbc); addComponent(tf_mail, 1, 6, gbc);
        addComponent(lb_languages, 0, 7, gbc); addComponent(tf_languages, 1, 7, gbc);
        addComponent(lb_skills, 0, 8, gbc); addComponent(tf_skills, 1, 8, gbc);
        addComponent(lb_hobbies, 0, 9, gbc); addComponent(tf_hobbies, 1, 9, gbc);

        // Formations & Expériences
        lb_formations = new JLabel("Formations: "); ta_formations = new JTextArea(5, 20);
        lb_experiences = new JLabel("Expériences: "); ta_experiences = new JTextArea(5, 20);
        addComponent(lb_formations, 0, 10, gbc); addComponent(new JScrollPane(ta_formations), 1, 10, gbc);
        addComponent(lb_experiences, 0, 11, gbc); addComponent(new JScrollPane(ta_experiences), 1, 11, gbc);

        // Buttons
        btnval = new JButton("Valider");
        btnval.setFont(new Font("Arial", Font.BOLD, 18));
        btnval.setBackground(Color.GREEN);  // Make it stand out more
        btnval.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 12; gbc.gridwidth = 2;
        this.add(btnval, gbc);

        // Quitter Button
        btnquitter = new JButton("Quitter");
        btnquitter.setFont(new Font("Arial", Font.BOLD, 18));
        btnquitter.setBackground(Color.RED);  // Quit button should have a different color
        btnquitter.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 13; gbc.gridwidth = 2;
        this.add(btnquitter, gbc);

        // Image Button (you can add functionality to select an image if needed)
        btnImage = new JButton("Ajouter Image");
        btnImage.setFont(new Font("Arial", Font.BOLD, 18));
        // Image Button Action Listener
        btnImage.addActionListener(e -> {
            // Create a JFileChooser to select image files
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionner une image");

            // Set file filter to allow only image files
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));

            // Show the file chooser dialog
            int result = fileChooser.showOpenDialog(this);

            // If the user selects a file
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                // Do something with the selected file, for example, display it in the UI
                // For now, we can just print the selected file's path
                System.out.println("Image sélectionnée: " + selectedFile.getAbsolutePath());

                // Optionally, you could add logic to display the image in your form or save its path to a field.
            }
        });

        gbc.gridx = 0; gbc.gridy = 14; gbc.gridwidth = 2;

        this.add(btnImage, gbc);

        // Génération du CV HTML
        btnval.addActionListener(e -> {
            // Retrieve values from input fields
            String nom = tf_nom.getText();
            String prenom = tf_prenom.getText();
            String pseudo = tf_pseudo.getText();
            String adresse = tf_adresse.getText();
            String tel = tf_tel.getText();
            String mail = tf_mail.getText();
            String formations = ta_formations.getText();
            String experiences = ta_experiences.getText();
            String skills = tf_skills.getText();
            String languages = tf_languages.getText();
            String hobbies = tf_hobbies.getText();

            // Validation for required fields
            if (nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty() || adresse.isEmpty() || tel.isEmpty() || mail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create the HTML content for the CV
            File f = new File("cv.html");
            try (FileWriter fw = new FileWriter(f, false)) {
                fw.write("<html lang='fr'><head>" +
                        "<meta charset='utf-8'><title>CV de " + nom + " " + prenom + "</title>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                        "<link rel='stylesheet' href='styles.css'></head><body>" +

                        "<header><ul class='container'>" +
                        "<li><h1><span id='prenom'>" + prenom + "</span> <span id='nom'>" + nom + "</span></h1></li>" +
                        "<li><strong>Adresse</strong> " + adresse + "</li>" +
                        "<li><strong>Téléphone</strong> " + tel + "</li>" +
                        "<li><strong>Mail</strong> " + mail + "</li></ul>" +
                             "\t<style>\n" +
                        "        \n" +
                        "html, body, div, span, applet, object, iframe,\n" +
                        "h1, h2, h3, h4, h5, h6, p, blockquote, pre,\n" +
                        "a, abbr, acronym, address, big, cite, code,\n" +
                        "del, dfn, em, img, ins, kbd, q, s, samp,\n" +
                        "small, strike, strong, sub, sup, tt, var,\n" +
                        "b, u, i, center,\n" +
                        "dl, dt, dd, ol, ul, li,\n" +
                        "fieldset, form, label, legend,\n" +
                        "table, caption, tbody, tfoot, thead, tr, th, td,\n" +
                        "article, aside, canvas, details, embed, \n" +
                        "figure, figcaption, footer, header, hgroup, \n" +
                        "menu, nav, output, ruby, section, summary,\n" +
                        "time, mark, audio, video {\n" +
                        "\tmargin: 0;\n" +
                        "\tpadding: 0;\n" +
                        "\tborder: 0;\n" +
                        "\tfont-size: 100%;\n" +
                        "\tfont: inherit;\n" +
                        "\tvertical-align: baseline;\n" +
                        "}\n" +
                        "/* HTML5 display-role reset for older browsers */\n" +
                        "article, aside, details, figcaption, figure, \n" +
                        "footer, header, hgroup, menu, nav, section {\n" +
                        "\tdisplay: block;\n" +
                        "}\n" +
                        "body {\n" +
                        "\tline-height: 1;\n" +
                        "    background: #fff;\n" +
                        "}\n" +
                        "ol, ul {\n" +
                        "\tlist-style: none;\n" +
                        "}\n" +
                        "blockquote, q {\n" +
                        "\tquotes: none;\n" +
                        "}\n" +
                        "blockquote:before, blockquote:after,\n" +
                        "q:before, q:after {\n" +
                        "\tcontent: '';\n" +
                        "\tcontent: none;\n" +
                        "}\n" +
                        "table {\n" +
                        "\tborder-collapse: collapse;\n" +
                        "\tborder-spacing: 0;\n" +
                        "}\n" +
                        "\n" +
                        "/**********************\n" +
                        "\n" +
                        "   Variable et Class\n" +
                        "\n" +
                        "**********************/\n" +
                        "\n" +
                        ":root {\n" +
                        "    --grisC: #cacaca;\n" +
                        "    --gris7: #8a8a8a;\n" +
                        "    --border: #aaa;\n" +
                        "    --bleu: #068ec6;\n" +
                        "    --texte: #555;\n" +
                        "    --bgNoir: #111;\n" +
                        "}\n" +
                        "\n" +
                        ".container {\n" +
                        "    width: 90%;\n" +
                        "    max-width: 90%;\n" +
                        "    margin: 0 auto;\n" +
                        "}\n" +
                        "\n" +
                        "section .container {\n" +
                        "    margin: 50px auto;\n" +
                        "    width: 80%;\n" +
                        "}\n" +
                        "\n" +
                        ".noBorder {\n" +
                        "    border: none !important;\n" +
                        "}\n" +
                        "\n" +
                        ".top10 {\n" +
                        "    margin-top: 10px;\n" +
                        "}\n" +
                        "\n" +
                        "/**********************\n" +
                        "\n" +
                        "          CV\n" +
                        "\n" +
                        "**********************/\n" +
                        "\n" +
                        "html {\n" +
                        "    background: var(--grisC);\n" +
                        "    min-width: 100vw;\n" +
                        "    min-height: 100vh;\n" +
                        "    font-family: Arial;\n" +
                        "}\n" +
                        "\n" +
                        "body {\n" +
                        "    border: 1px solid var(--border);\n" +
                        "    background: #fff;\n" +
                        "    width: 90%;\n" +
                        "    max-width: 900px;\n" +
                        "    min-height: calc(90vh - 2px);\n" +
                        "    margin: 5vh auto;\n" +
                        "    border-radius: 5px;\n" +
                        "    box-shadow: 0px 5px 50px var(--gris7);\n" +
                        "}\n" +
                        "\n" +
                        "header {\n" +
                        "    background: var(--bgNoir);\n" +
                        "    height: 150px;\n" +
                        "    color: #fff;\n" +
                        "}\n" +
                        "\n" +
                        "header ul {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: 2fr 2fr 1.5fr 1.5fr;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li {\n" +
                        "    height: 150px;\n" +
                        "    padding: 50px 0 0 15%;\n" +
                        "    font-size: 15px;\n" +
                        "    color: #aaa;\n" +
                        "    line-height: 1.5;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li strong {\n" +
                        "    display: block;\n" +
                        "    text-transform: uppercase;\n" +
                        "    color: #eee;\n" +
                        "    font-size: 14px;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li:nth-child(2) {\n" +
                        "    padding-left: 20%;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li:nth-child(3) {\n" +
                        "    padding-left: 15%;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li:first-child {\n" +
                        "    background: var(--bleu);\n" +
                        "    color: #fff;\n" +
                        "    height: 110px;\n" +
                        "    padding: 40px 16% 0 16%;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li #prenom, header ul li #nom {\n" +
                        "    display: block;\n" +
                        "    font-size: 28px;\n" +
                        "    line-height: 1.25;\n" +
                        "    text-transform: uppercase;\n" +
                        "}\n" +
                        "\n" +
                        "header ul li #nom {\n" +
                        "    font-weight: bold;\n" +
                        "    font-size: 30px;\n" +
                        "}\n" +
                        "\n" +
                        "main {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: 2fr 5fr;\n" +
                        "}\n" +
                        "\n" +
                        "aside {\n" +
                        "    background: #e5eaeb;\n" +
                        "    min-height: calc(100vh - 150px);\n" +
                        "    padding:0 10%;\n" +
                        "}\n" +
                        "\n" +
                        "aside img {\n" +
                        "    width: 100%;\n" +
                        "    margin: 60px auto 60px;\n" +
                        "    display: block;\n" +
                        "    border-radius: 50%;\n" +
                        "}\n" +
                        "\n" +
                        "aside h2, section article h2 {\n" +
                        "    font-size: 18px;\n" +
                        "    font-weight: bold;\n" +
                        "    text-transform: uppercase;\n" +
                        "    margin-bottom: 20px;\n" +
                        "}\n" +
                        "\n" +
                        "aside p {\n" +
                        "    color: var(--texte);\n" +
                        "    margin-bottom: 30px;\n" +
                        "    padding-bottom: 30px;\n" +
                        "    border-bottom: 1px solid #cecece;\n" +
                        "    line-height: 1.4;\n" +
                        "}\n" +
                        "\n" +
                        "section article {\n" +
                        "    border-top: 1px solid #cecece;\n" +
                        "    margin-bottom: 50px;\n" +
                        "}\n" +
                        "\n" +
                        "section article h2 {\n" +
                        "    width: auto;\n" +
                        "    background: #fff;\n" +
                        "    border-right: 1px solid #cecece;\n" +
                        "    position: relative;\n" +
                        "    top: -10px;\n" +
                        "    display: inline-block;\n" +
                        "    padding-right: 10px;\n" +
                        "}\n" +
                        "\n" +
                        "section article p {\n" +
                        "    color: var(--texte);\n" +
                        "    line-height: 1.2;\n" +
                        "}\n" +
                        "\n" +
                        "section article li.exp {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: 105px auto;\n" +
                        "    margin-bottom: 20px;\n" +
                        "}\n" +
                        "\n" +
                        "section article li span.periode {\n" +
                        "    border-right: 1px solid var(--border);;\n" +
                        "    font-weight: bold;\n" +
                        "    display: flex;\n" +
                        "    flex-direction: column;\n" +
                        "    justify-content: center;\n" +
                        "}\n" +
                        "\n" +
                        "section article li.exp span.desc {\n" +
                        "    padding-left: 20px;\n" +
                        "    line-height: 1.4;\n" +
                        "    color: var(--texte);\n" +
                        "}\n" +
                        "\n" +
                        "section article li.exp span.desc strong {\n" +
                        "    font-weight: bold;   \n" +
                        "}\n" +
                        "\n" +
                        "footer {\n" +
                        "    background: var(--bgNoir);\n" +
                        "    height: 50px;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: 2fr 5fr;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul li:first-child {\n" +
                        "    background: var(--bleu);\n" +
                        "    background: #444;\n" +
                        "    height: 50px;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul li:nth-child(2) img {\n" +
                        "    height: 14px;\n" +
                        "    position: relative;\n" +
                        "    top: 3px;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul li:nth-child(2) {\n" +
                        "    display: block;\n" +
                        "    text-align: center;\n" +
                        "    width: 100%;\n" +
                        "    height: 35px;\n" +
                        "    padding-top: 15px;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul li:nth-child(2) a {\n" +
                        "    color: #999;\n" +
                        "    text-decoration: none;\n" +
                        "    font-size: 12px;\n" +
                        "}\n" +
                        "\n" +
                        "footer ul li:nth-child(2) a:hover {\n" +
                        "    text-decoration: underline;\n" +
                        "}\n" +
                        "\n" +
                        "/**********************\n" +
                        "\n" +
                        "     MEDIA QUERY\n" +
                        "\n" +
                        "**********************/\n" +
                        "@media (max-width: 1024px) {\n" +
                        "\n" +
                        "    header ul li #prenom, header ul li #nom {\n" +
                        "        font-size: 22px;\n" +
                        "    }\n" +
                        "    header ul li:first-child {\n" +
                        "        padding: 50px 16% 0 16%;\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "    header ul li {\n" +
                        "        font-size: 13px;\n" +
                        "    }\n" +
                        "    header ul li strong {\n" +
                        "        font-size: 14px;\n" +
                        "    }\n" +
                        "    \n" +
                        "}\n" +
                        "\n" +
                        "@media (max-width: 750px) {\n" +
                        "    \n" +
                        "    body {\n" +
                        "        width: 98%;\n" +
                        "        margin: 1vh auto;\n" +
                        "    }\n" +
                        "    .container {\n" +
                        "        width: 100%;\n" +
                        "        max-width: 100%;\n" +
                        "    }\n" +
                        "    header {\n" +
                        "        height: auto;\n" +
                        "    }\n" +
                        "    header ul {\n" +
                        "        grid-template-columns: 1fr;\n" +
                        "    }\n" +
                        "    header ul li:first-child {\n" +
                        "        height: auto;\n" +
                        "        padding: 20px 0;\n" +
                        "    }\n" +
                        "    header ul li:nth-child(2) {\n" +
                        "        padding-left: 0%;\n" +
                        "        padding-top: 30px;\n" +
                        "    }\n" +
                        "    header ul li:nth-child(3) {\n" +
                        "        padding: 10px 0%;\n" +
                        "    }\n" +
                        "    header ul li:nth-child(4) {\n" +
                        "        padding-bottom: 30px; \n" +
                        "        padding-left: 0%;\n" +
                        "    }\n" +
                        "    header ul li {\n" +
                        "        height: auto;\n" +
                        "        padding: 20px 0;\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "    aside {\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "    aside img {\n" +
                        "        max-width: 300px;\n" +
                        "    }\n" +
                        "    main {\n" +
                        "        grid-template-columns: 1fr;\n" +
                        "    }\n" +
                        "    section article, section article h2 {\n" +
                        "        border: none;\n" +
                        "    }\n" +
                        "    section article li.exp {\n" +
                        "        grid-template-columns: 85px auto;\n" +
                        "    }\n" +
                        "    section article li span.periode {\n" +
                        "        font-size: 12px;\n" +
                        "    }\n" +
                        "    footer ul {\n" +
                        "        grid-template-columns: 0fr 1fr;\n" +
                        "    }\n" +
                        "}\n" +
                        "    </style>\n" +
                        "\n"+
                        "</header>" +

                        "<main class='container'><aside>" +
                        (selectedFile != null ? "<img src='" + selectedFile.toURI().toString() + "' alt='Photo du CV'>" : "") +
                        "<article><h2>Compétences</h2><p>" + skills + "</p></article>" +
                        "<article><h2>Langues</h2><p>" + languages + "</p></article>" +
                        "<article><h2>Centre d'intérêt</h2><p class='noBorder'>" + hobbies + "</p></article>" +
                        "</aside><section><div class='container'>" +

                        "<article><h2>Formations et diplômes</h2><ul><li>" + formations.replace("\n", "</li><li>") + "</li></ul></article>" +
                        "<article><h2>Expérience</h2><ul class='top10'><li>" + experiences.replace("\n", "</li><li>") + "</li></ul></article>" +

                        "</div></section></main></body></html>");
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void addComponent(Component component, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        this.add(component, gbc);
    }
}
