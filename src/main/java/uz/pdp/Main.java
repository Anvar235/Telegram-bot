package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.model.DefItem;
import uz.pdp.model.Result;
import uz.pdp.model.TrItem;
import uz.pdp.tranlation.Translate;

import java.util.ArrayList;
import java.util.List;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) {

        try {

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Main());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "dictionaryAnvarBot";
    }

    @Override
    public String getBotToken() {
        return "1867707489:AAFsD8UQhZq5LOP5mUpIeS_lC2zC3asjzas";
    }

    int level = 0;
    String language = "";

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        Long chatId;
        String text = "";


        if (update.hasMessage()) {

            chatId = update.getMessage().getChatId();
            text = update.getMessage().getText();

            if (text.equals("/start")) {

                sendMessage.setText("Yandex botga xush kelibsiz!\n \nTarjima qilmoqchi bo'lgan tilingizni tanlang!");
                level = 1;

//            } else if (text.equals("Tillar ro'yhati")) {
//
//                sendMessage.setText("Tillar ro'yxati");
//                level = 2;

            } else if ((text.equals("ru-en") || text.equals("en-ru")) || (text.equals("ru-tr") || text.equals("tr-ru")) || (text.equals("tr-en") || text.equals("en-tr"))) {

                language = text;
                sendMessage.setText("qidirayotgan so'zni kiriting:");
                level = 2;

            } else {

                level = 3;

            }

            sendMessage.setChatId(String.valueOf(chatId));

            switch (level) {

                case 0:

                    chooseLang(sendMessage);
                    level = 1;
                    break;

                case 1:

                    getLang(sendMessage);
                    level = 2;
                    break;

                case 2:

                    level = 3;
                    break;

                case 3:

                    StringBuilder stringBuilder = new StringBuilder();

                    Result result = Translate.lookUp(language, text);
                    List<DefItem> def = result.getDef();

                    for (DefItem defItem : def) {

                        List<TrItem> tr = defItem.getTr();

                        for (TrItem trItem : tr) {

                            stringBuilder.append(trItem.getText()).append("\n");

                        }

                    }

                    sendMessage.setChatId(String.valueOf(chatId));
                    sendMessage.setText(stringBuilder.toString());
                    break;

            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void chooseLang(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(new KeyboardButton("Tillar ro'yhati"));
        secondRow.add(new KeyboardButton("Info"));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    public void getLang(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();

        String[] langs = Translate.GetLangs();

        for (int i = 0; i < langs.length; i++) {

            KeyboardRow firstRow = new KeyboardRow();
            KeyboardRow secondRow = new KeyboardRow();
            KeyboardRow thirdRow = new KeyboardRow();

            if (langs[i].equals("ru-en")) {

                firstRow.add(new KeyboardButton("ru-en"));
                firstRow.add(new KeyboardButton("en-ru"));
                keyboardRows.add(firstRow);

            } else if (langs[i].equals("ru-tr")) {

                secondRow.add(new KeyboardButton("ru-tr"));
                secondRow.add(new KeyboardButton("tr-ru"));
                keyboardRows.add(secondRow);

            } else if (langs[i].equals("en-tr")) {

                thirdRow.add(new KeyboardButton("en-tr"));
                thirdRow.add(new KeyboardButton("tr-en"));
                keyboardRows.add(thirdRow);

            }
        }

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

}
