package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleValidation(Exception ex) {

//        telegramBot.sendMessage(7882316826L, ex.getMessage(), "admin");
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "success", false,
                        "code", "VALIDATION_ERROR",
                        "message", Map.ofEntries(
                                Map.entry("uz", "Yuborilgan maʼlumotlar noto‘g‘ri"),
                                Map.entry("ru", "Отправленные данные недействительны"),
                                Map.entry("en", "Submitted data is invalid"),
                                Map.entry("tr", "Gönderilen veriler geçersiz"),
                                Map.entry("kk", "Жіберілген деректер жарамсыз"),
                                Map.entry("ky", "Жөнөтүлгөн маалыматтар жараксыз"),
                                Map.entry("tg", "Фиристодашуда маълумот нодуруст аст"),
                                Map.entry("az", "Göndərilən məlumatlar etibarsızdır"),
                                Map.entry("ar", "البيانات المرسلة غير صالحة"),
                                Map.entry("fa", "داده‌های ارسال شده نامعتبر است"),
                                Map.entry("zh", "提交的数据无效"),
                                Map.entry("ja", "送信されたデータは無効です"),
                                Map.entry("ko", "제출된 데이터가 유효하지 않습니다"),
                                Map.entry("de", "Die übermittelten Daten sind ungültig"),
                                Map.entry("fr", "Les données soumises ne sont pas valides"),
                                Map.entry("es", "Los datos enviados no son válidos"),
                                Map.entry("it", "I dati inviati non sono validi"),
                                Map.entry("pt", "Os dados enviados são inválidos"),
                                Map.entry("hi", "भेजा गया डेटा अमान्य है"),
                                Map.entry("ur", "بھیجا گیا ڈیٹا غلط ہے"),
                                Map.entry("bn", "প্রেরিত ডেটা অবৈধ"),
                                Map.entry("id", "Data yang dikirim tidak valid"),
                                Map.entry("ms", "Data yang dihantar tidak sah"),
                                Map.entry("th", "ข้อมูลที่ส่งไม่ถูกต้อง"),
                                Map.entry("vi", "Dữ liệu đã gửi không hợp lệ"),
                                Map.entry("tl", "Ang ipinadalang data ay hindi wasto"),
                                Map.entry("my", "ပေးပို့ထားသော ဒေတာသည် မမှန်ကန်ပါ"),
                                Map.entry("km", "ទិន្នន័យដែលបានផ្ញើមិនត្រឹមត្រូវទេ"),
                                Map.entry("lo", "ຂໍ້ມູນທີ່ສົ່ງມາແມ່ນບໍ່ຖືກຕ້ອງ"),
                                Map.entry("si", "යවන ලද දත්ත වලංගු නොවේ"),
                                Map.entry("ne", "पठाइएको डेटा अमान्य छ"),
                                Map.entry("ta", "அனுப்பப்பட்ட தரவு தவறானது"),
                                Map.entry("te", "పంపిన డేటా చెల్లదు"),
                                Map.entry("kn", "ಕಳುಹಿಸಿದ ಡೇಟಾ ಅಮಾನ್ಯವಾಗಿದೆ"),
                                Map.entry("ml", "അയച്ച ഡാറ്റ അസാധുവാണ്"),
                                Map.entry("mr", "पाठवलेला डेटा अवैध आहे"),
                                Map.entry("gu", "મોકલેલ ડેટા અમાન્ય છે"),
                                Map.entry("pa", "ਭੇਜਿਆ ਡੇਟਾ ਅਵੈਧ ਹੈ"),
                                Map.entry("or", "ପଠାଯାଇଥିବା ତଥ୍ୟ ଅବୈଧ"),
                                Map.entry("as", "প্ৰেৰণ কৰা তথ্য অবৈধ"),
                                Map.entry("sd", "موڪليل ڊيٽا غلط آهي"),
                                Map.entry("ps", "لېږل شوې ډاټا ناسمه ده"),
                                Map.entry("ku", "Daneyên şandî nederbasdar e"),
                                Map.entry("ckb", "داتای نێردراو نادروستە"),
                                Map.entry("he", "הנתונים שנשלחו אינם תקינים"),
                                Map.entry("yi", "די דאטן וואס איז געשיקט איז אומגילטיק"),
                                Map.entry("el", "Τα δεδομένα που υποβλήθηκαν δεν είναι έγκυρα"),
                                Map.entry("nl", "De verzonden gegevens zijn ongeldig"),
                                Map.entry("sv", "De skickade uppgifterna är ogiltiga"),
                                Map.entry("no", "De innsendte dataene er ugyldige"),
                                Map.entry("da", "De indsendte data er ugyldige"),
                                Map.entry("fi", "Lähetetyt tiedot ovat virheellisiä"),
                                Map.entry("is", "Gögnin sem send voru eru ógild"),
                                Map.entry("pl", "Przesłane dane są nieprawidłowe"),
                                Map.entry("cs", "Odeslaná data jsou neplatná"),
                                Map.entry("sk", "Odoslané údaje sú neplatné"),
                                Map.entry("hu", "A beküldött adatok érvénytelenek"),
                                Map.entry("ro", "Datele trimise sunt invalide"),
                                Map.entry("bg", "Изпратените данни са невалидни"),
                                Map.entry("sr", "Послати подаци су неважећи"),
                                Map.entry("hr", "Poslani podaci nisu valjani"),
                                Map.entry("bs", "Poslani podaci nisu valjani"),
                                Map.entry("sl", "Poslani podatki niso veljavni"),
                                Map.entry("mk", "Испратените податоци се неважечки"),
                                Map.entry("sq", "Të dhënat e dërguara janë të pavlefshme"),
                                Map.entry("lt", "Pateikti duomenys neteisingi"),
                                Map.entry("lv", "Iesniegtie dati nav derīgi"),
                                Map.entry("et", "Esitatud andmed on kehtetud"),
                                Map.entry("be", "Адпраўленыя даныя несапраўдныя"),
                                Map.entry("uk", "Надіслані дані недійсні"),
                                Map.entry("hy", "Ուղարկված տվյալները անվավեր են"),
                                Map.entry("ka", "გაგზავნილი მონაცემები არასწორია"),
                                Map.entry("mn", "Илгээсэн өгөгдөл хүчингүй байна"),
                                Map.entry("bo", "བཏང་བའི་གནས་ཚུལ་ནི་ནོར་འཁྲུལ་ཡིན།"),
                                Map.entry("ug", "ئەۋەتىلگەن سانلىق مەلۇمات ئىناۋەتسىز"),
                                Map.entry("tt", "Җибәрелгән мәгълүматлар дөрес түгел"),
                                Map.entry("ba", "Ебәрелгән мәғлүмәттәр дөрөҫ түгел"),
                                Map.entry("cv", "Янӑтнӑ даннӑйсем йӗркесӗр"),
                                Map.entry("sah", "Ыымыллыбыт дааннайдар сөптөөх аҥардах"),
                                Map.entry("ce", "ДӀадаьхьна долу хаамаш нийса дац"),
                                Map.entry("os", "Æрвыст бæрæггæнæнтæ раст не сты"),
                                Map.entry("ab", "Иаҵәа адыррақәа иашам"),
                                Map.entry("av", "Кьуна базаялъул данныялъул рекъон гьечӣ"),
                                Map.entry("kbd", "Егъэхьа даныхэр пэжкъым"),
                                Map.entry("kv", "Ыстыӧм даннӧйясӧн веськыдӧсь"),
//                                Map.entry("kv", "Ыстыӧм даннӧйясӧн веськыдӧсь"),
                                Map.entry("mdf", "Кучфтефксне аф видеса"),
                                Map.entry("myv", "Кучозь датойтне арасть видевть"),
                                Map.entry("udm", "Кучем данойос ӧвӧл шонер"),
                                Map.entry("koi", "Кучӧм даннӧйесӧ абу веськытӧсь"),
                                Map.entry("kpv", "Ыстӧм даннӧйяс абуӧсь веськыдӧсь"),
                                Map.entry("mhr", "Колтымо данный-влак чын огытыл"),
                                Map.entry("mrj", "Колтымы данныевлӓ чын агыл"),
//                                Map.entry("fi", "Lähetetyt tiedot ovat virheellisiä"),
                                Map.entry("se", "Sáddejuvvon dáhtat eai leat dohkkehahtton"),
                                Map.entry("smn", "Vuolgâttum tiäđuh iä lah vuoigâdlâččah"),
                                Map.entry("sms", "Vuõlggâdum teâđah lij âʹvvel"),
                                Map.entry("izh", "Lähetetyt tiedot ovat virheellisiä"),
                                Map.entry("vot", "Lähetetyd tiedot ovat virheellisiä"),
                                Map.entry("liv", "Sõndõtõd tieudõd äb ūoõigõd"),
                                Map.entry("vep", "Oigendatud andmused oma välläd"),
                                Map.entry("krl", "Työnnetyt tiedot ollah vällät"),
                                Map.entry("olo", "Työnnetyt tiedot oldah vällät"),
                                Map.entry("lud", "Työnnetyt tiedot oldah vällät"),
                                Map.entry("fit", "Lähetetyt tiedot ovat virheellisiä"),
                                Map.entry("tolk", "Lähetetyt tiedot ovat virheellisiä")
                        ),
                        "error", ex.getMessage()
                )
        );
    }


}
