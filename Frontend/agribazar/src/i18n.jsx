import i18n from "i18next";
import { initReactI18next } from "react-i18next";

i18n
  .use(initReactI18next)
  .init({
    resources: {
      en: {
        translation: {
          login: "Login",
          wishlist:"wishlist",
          cart:"cart",
          track_order:"Track Order",
          fungicides: "Fungicides",
          insecticides: "Insecticides",
          herbicides: "Herbicides",
          seeds: "Seeds",
          fertilizers: "Fertilizers",
          nutrients:"Nutrients",

          shreeDhokeshwar: "Shree Dhokeshwar Krushi Udyog",
          bhumiputra: "Bhumiputra Krushi Seva",
          balaji: "Balaji Krushi Seva",
          natha: "Natha Farming",
          jadhav: "JADHAV Krushi Kendra",
          ujjwal: "Ujjwal Krushi Udyog"
        }
      },
      hi: {
        translation: {
          login: "लॉगिन",
          wishlist:"इच्छा सूची",
          cart:"कार्ट",
          track_order:"ट्रॅक ऑर्डर",
           fungicides: "फफूंदनाशक",
          insecticides: "कीटनाशक",
          herbicides: "खरपतवारनाशक",
          seeds: "बीज",
          fertilizers: "उर्वरक",
          nutrients:"पोषक तत्व",

          shreeDhokeshwar: "श्री ढोकेश्वर कृषि उद्योग",
          bhumiputra: "भूमिपुत्र कृषि सेवा",
          balaji: "बालाजी कृषि सेवा",
          natha: "नाथा फार्मिंग",
          jadhav: "जाधव कृषि केंद्र",
          ujjwal: "उज्ज्वल कृषि उद्योग"
        }
      },
      mr: {
        translation: {
          login: "लॉगिन",
          wishlist:"इच्छा सूची",
          cart:"कार्ट",
          track_order:"ट्रॅक ऑर्डर",
          fungicides: "बुरशीनाशके",
          insecticides: "कीटकनाशके",
          herbicides: "तणनाशके",
          seeds: "बियाणे",
          fertilizers: "खते",
        nutrients:"पोषक तत्वे",
          shreeDhokeshwar: "श्री ढोकेश्वर कृषी उद्योग",
          bhumiputra: "भूमिपुत्र कृषी सेवा",
          balaji: "बालाजी कृषी सेवा",
          natha: "नाथा फार्मिंग",
          jadhav: "जाधव कृषी केंद्र",
          ujjwal: "उज्ज्वल कृषी उद्योग"



        }
      }
    },
    lng: localStorage.getItem("language") || "en",
    fallbackLng: "en",
    interpolation: {
      escapeValue: false
    }
  });

export default i18n;