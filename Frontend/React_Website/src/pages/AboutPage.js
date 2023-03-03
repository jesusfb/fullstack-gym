import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";
import gympic from "../media/gympic.png";
import Footer from "../components/Footer";

export default function AboutPage() {

  return (
    <>
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">About Our Gym</h2>
          <img src={gympic} style={{width: "100%"}} />
Στα Muscle Project πιστεύουμε ότι η άσκηση δεν είναι πολυτέλεια! Στόχος μας είναι να προσφέρουμε τις καλύτερες υπηρεσίες άθλησης στις καλύτερες τιμές ώστε όλοι να μπορούν να εντάξουν το fitness στην καθημερινότητά τους. Η φιλοσοφία μας στηρίζεται στην διατήρηση της καλής υγείας και  ευεξίας, ώστε ο καθένας να μπορεί να βελτιώσει την ποιότητα ζωής και της φυσικής του κατάστασης.
Μια μεγάλη ποικιλία paid και free ομαδικών προγραμμάτων είναι διαθέσιμα για να τα δοκιμάσεις και να ανακαλύψεις νέες προκλήσεις και νέες μορφές άσκησης. Στα Muscle Project δεσμευόμαστε να προσφέρουμε συνεχώς ό,τι νεότερο και καινοτόμο στον παγκόσμιο χώρο του fitness. Σε περιμένουμε σε ένα από τα PLANET Fitness & More clubs για να γνωριστούμε και ν’ ανακαλύψουμε μαζί την ιδανική μορφή άσκησης που σου ταιριάζει!

        </div>
      </div>
    </div>
    <Footer />
    </>
  );
}
