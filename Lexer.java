/* The following code was generated by JFlex 1.6.1 */

import java_cup.runtime.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>Lexer.flex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\50\1\47\1\52\1\51\1\47\22\0\1\34\1\0\1\24"+
    "\4\0\1\23\1\41\1\42\1\31\1\32\1\44\1\32\1\43\1\31"+
    "\12\1\2\0\1\25\1\27\1\26\2\0\1\16\1\13\1\21\1\30"+
    "\1\10\1\35\1\11\1\22\1\5\2\2\1\15\1\33\1\6\1\14"+
    "\1\37\1\2\1\12\1\20\1\7\1\36\1\40\1\45\1\46\2\2"+
    "\4\0\1\3\1\0\1\16\1\13\1\21\1\30\1\10\1\35\1\11"+
    "\1\22\1\5\2\2\1\15\1\33\1\6\1\14\1\37\1\2\1\12"+
    "\1\20\1\7\1\36\1\40\1\45\1\46\2\2\12\0\1\52\252\0"+
    "\2\4\115\0\1\17\u1ea8\0\1\52\1\52\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\1\10\3\1\1\2\3"+
    "\1\1\1\4\3\5\1\3\1\6\1\7\1\3\1\10"+
    "\2\3\1\11\1\12\1\13\1\14\1\3\1\15\1\16"+
    "\1\17\1\0\1\20\1\3\1\20\2\3\1\21\5\3"+
    "\1\22\1\3\2\23\2\0\3\3\1\0\1\10\2\0"+
    "\10\3\1\0\2\3\1\24\2\3\1\0\3\3\1\25"+
    "\2\0\1\26\2\3\1\26\1\3\1\27\2\30\1\31"+
    "\1\32\1\3\1\0\3\3\1\0\1\3\1\0\1\3"+
    "\1\33\1\34\1\0\2\35\2\3\2\0\2\3\1\36"+
    "\1\3\2\0\3\3\1\37\1\0\1\3\1\0\1\3"+
    "\4\0\3\3\2\0\3\3\1\40\1\0\1\40\1\3"+
    "\1\0\1\3\2\41\1\0\1\42\2\0\2\43\1\0"+
    "\2\3\1\36\2\44\1\0\1\3\1\0\1\3\2\45"+
    "\1\46\2\0\1\3\1\0\1\3\2\47\1\0\2\50"+
    "\2\51\3\0\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[180];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\53\0\126\0\201\0\254\0\327\0\u0102\0\u012d"+
    "\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f\0\u025a\0\u0285"+
    "\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\126\0\u0387\0\126"+
    "\0\126\0\u03b2\0\u03dd\0\u0408\0\u0433\0\126\0\126\0\126"+
    "\0\126\0\u045e\0\u0489\0\126\0\126\0\u04b4\0\126\0\u04df"+
    "\0\254\0\u050a\0\u0535\0\254\0\u0560\0\u058b\0\u05b6\0\u05e1"+
    "\0\u060c\0\254\0\u0637\0\126\0\254\0\u0662\0\u068d\0\u06b8"+
    "\0\u06e3\0\u070e\0\u02db\0\u0739\0\u0764\0\u078f\0\u07ba\0\u07e5"+
    "\0\u0810\0\u083b\0\u0866\0\u0891\0\u08bc\0\u08e7\0\u0912\0\u093d"+
    "\0\u0968\0\254\0\u0993\0\u09be\0\u09e9\0\u0a14\0\u0a3f\0\u0a6a"+
    "\0\254\0\u0a95\0\u0ac0\0\126\0\u0aeb\0\u0b16\0\254\0\u0b41"+
    "\0\126\0\126\0\254\0\254\0\254\0\u0b6c\0\u0b97\0\u0bc2"+
    "\0\u0bed\0\u0c18\0\u0c43\0\u0c6e\0\u0c99\0\u0cc4\0\254\0\254"+
    "\0\u0cef\0\u0d1a\0\u0d45\0\u0d70\0\u0d9b\0\u0dc6\0\u0df1\0\u0e1c"+
    "\0\u0e47\0\254\0\u0e72\0\u0e9d\0\u0ec8\0\u0ef3\0\u0f1e\0\u0f49"+
    "\0\254\0\u0f74\0\u0f9f\0\u0fca\0\u0ff5\0\u1020\0\u104b\0\u1076"+
    "\0\u10a1\0\u10cc\0\u10f7\0\u1122\0\u114d\0\u1178\0\u11a3\0\u11ce"+
    "\0\u11f9\0\126\0\u1224\0\254\0\u124f\0\u127a\0\u12a5\0\126"+
    "\0\254\0\u12d0\0\126\0\u12fb\0\u1326\0\126\0\254\0\u1351"+
    "\0\u137c\0\u13a7\0\126\0\126\0\254\0\u13d2\0\u13fd\0\u1428"+
    "\0\u1453\0\126\0\254\0\126\0\u147e\0\u14a9\0\u14d4\0\u14ff"+
    "\0\u152a\0\126\0\254\0\u1555\0\126\0\254\0\126\0\254"+
    "\0\u1580\0\u15ab\0\u15d6\0\126";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[180];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\3\1\6\1\7\1\10\1\11"+
    "\1\12\1\5\1\13\1\14\1\15\1\5\1\16\1\17"+
    "\1\20\1\21\1\5\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\5\1\35"+
    "\1\5\1\36\1\37\1\40\1\41\1\42\1\5\1\43"+
    "\2\33\1\3\24\44\1\45\22\44\1\3\1\44\2\3"+
    "\54\0\1\4\52\0\3\5\1\0\12\5\1\0\3\5"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\2\5"+
    "\12\0\1\46\26\0\1\47\16\0\3\5\1\0\1\5"+
    "\1\50\10\5\1\0\3\5\5\0\1\5\2\0\1\5"+
    "\1\0\1\51\3\5\4\0\2\5\5\0\3\5\1\0"+
    "\3\5\1\52\3\5\1\53\2\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\5\0"+
    "\3\5\1\0\7\5\1\54\2\5\1\0\2\5\1\55"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\2\5"+
    "\5\0\3\5\1\0\1\5\1\56\6\5\1\57\1\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\3\5\1\60\6\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\7\5\1\61\2\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\5\5\1\62\4\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\1\5\1\63\10\5"+
    "\1\64\1\65\2\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\13\0\1\66\26\0\1\67\15\0"+
    "\3\5\1\0\2\5\1\70\7\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\1\5\1\71\2\5\4\0"+
    "\2\5\5\0\3\5\1\0\12\5\1\0\2\5\1\72"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\2\5"+
    "\4\0\47\73\1\74\3\73\1\0\2\75\2\0\12\75"+
    "\1\0\3\75\5\0\1\75\2\0\1\75\1\0\4\75"+
    "\4\0\2\75\32\0\2\26\52\0\1\26\24\0\3\5"+
    "\1\76\1\77\11\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\5\0\3\5\1\0"+
    "\7\5\1\100\2\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\40\0\1\33\13\0"+
    "\2\33\2\0\3\5\1\0\7\5\1\101\2\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\1\5\1\102"+
    "\2\5\4\0\2\5\5\0\3\5\1\0\5\5\1\103"+
    "\4\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\1\5\1\104\2\5\4\0\2\5\5\0\3\5\1\0"+
    "\3\5\1\105\6\5\1\0\2\5\1\106\5\0\1\5"+
    "\2\0\1\5\1\0\4\5\4\0\2\5\53\0\1\43"+
    "\12\0\1\107\44\0\3\5\1\0\2\5\1\110\7\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\12\5\1\0\3\5"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\1\5"+
    "\1\111\5\0\3\5\1\0\2\5\1\112\7\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\3\5\1\113\6\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\12\5\1\0\3\5\5\0"+
    "\1\114\2\0\1\5\1\0\4\5\4\0\2\5\5\0"+
    "\3\5\1\0\12\5\1\115\1\116\2\5\5\0\1\5"+
    "\2\0\1\5\1\0\4\5\4\0\2\5\5\0\3\5"+
    "\1\0\11\5\1\117\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\5\0\3\5\1\0"+
    "\7\5\1\120\2\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\5\0\3\5\1\0"+
    "\12\5\1\0\3\5\5\0\1\121\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\16\0\1\122\3\0\1\123\47\0"+
    "\1\124\40\0\3\5\1\0\5\5\1\125\3\5\1\126"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\6\5\1\127\3\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\11\5\1\130\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\53\0\1\74\27\0\1\131\61\0\1\132\20\0"+
    "\3\5\1\0\12\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\133\1\0\4\5\4\0\2\5\5\0\3\5\1\0"+
    "\12\5\1\0\3\5\5\0\1\134\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\5\5\1\135"+
    "\4\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\1\5\1\136"+
    "\10\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\137\1\140\11\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\6\5\1\141\3\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\1\5\1\142\10\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\143\1\144\11\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\14\0\1\145\43\0\3\5\1\0\3\5\1\146"+
    "\6\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\2\5\1\147"+
    "\7\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\1\5\1\150"+
    "\10\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\12\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\151\4\5\4\0"+
    "\2\5\14\0\1\152\43\0\3\5\1\0\3\5\1\153"+
    "\6\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\5\0\3\5\1\0\12\5\1\0"+
    "\3\5\5\0\1\154\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\10\5\1\155\1\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\10\0\2\156\54\0\1\157\44\0\3\5\1\156"+
    "\1\160\11\5\1\0\3\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\5\0\3\5\1\0\2\5"+
    "\1\161\7\5\1\0\3\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\5\0\3\5\1\0\5\5"+
    "\1\162\4\5\1\0\3\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\5\0\3\5\1\0\12\5"+
    "\1\0\1\5\1\163\1\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\12\0\1\164\31\0\1\165"+
    "\13\0\3\5\1\0\1\5\1\166\10\5\1\0\3\5"+
    "\5\0\1\5\2\0\1\5\1\0\3\5\1\167\4\0"+
    "\2\5\5\0\3\5\1\0\10\5\1\170\1\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\12\5\1\0\3\5\5\0"+
    "\1\171\2\0\1\5\1\0\4\5\4\0\2\5\21\0"+
    "\1\172\36\0\3\5\1\0\10\5\1\173\1\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\15\0\1\174\42\0\3\5\1\0\4\5\1\175"+
    "\5\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\10\0\2\176\11\0\2\177\14\0"+
    "\1\200\21\0\2\201\46\0\3\5\1\201\1\202\11\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\10\5\1\203\1\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\5\0\3\5\1\0\3\5\1\204\6\5"+
    "\1\0\3\5\5\0\1\5\2\0\1\5\1\0\4\5"+
    "\4\0\2\5\12\0\1\205\50\0\2\206\46\0\3\5"+
    "\1\0\1\5\1\207\10\5\1\0\3\5\5\0\1\5"+
    "\2\0\1\5\1\0\4\5\4\0\2\5\5\0\3\5"+
    "\1\206\1\210\11\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\5\0\3\5\1\0"+
    "\2\5\1\211\7\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\13\0\1\212\61\0"+
    "\1\213\35\0\3\5\1\0\2\5\1\214\7\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\11\5\1\215\1\0\3\5"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\2\5"+
    "\5\0\3\5\1\216\1\217\11\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\14\0"+
    "\1\220\43\0\3\5\1\0\3\5\1\221\6\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\14\0\1\222\43\0\3\5\1\0\3\5\1\130"+
    "\6\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\41\0\1\223\53\0\1\224\52\0"+
    "\1\225\51\0\1\226\16\0\3\5\1\0\12\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\1\227\3\5"+
    "\4\0\2\5\5\0\3\5\1\230\1\231\11\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\5\0\3\5\1\0\11\5\1\232\1\0\3\5"+
    "\5\0\1\5\2\0\1\5\1\0\4\5\4\0\2\5"+
    "\15\0\1\233\62\0\1\234\32\0\3\5\1\0\4\5"+
    "\1\162\5\5\1\0\3\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\5\0\3\5\1\0\12\5"+
    "\1\0\1\5\1\235\1\5\5\0\1\5\2\0\1\5"+
    "\1\0\4\5\4\0\2\5\5\0\3\5\1\236\1\237"+
    "\11\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\13\0\1\240\44\0\3\5\1\0"+
    "\2\5\1\241\7\5\1\0\3\5\5\0\1\5\2\0"+
    "\1\5\1\0\4\5\4\0\2\5\25\0\1\242\32\0"+
    "\3\5\1\0\12\5\1\0\1\5\1\243\1\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\16\0"+
    "\1\233\53\0\1\244\45\0\1\245\52\0\1\246\45\0"+
    "\3\5\1\0\1\5\1\247\10\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\5\0"+
    "\3\5\1\0\1\5\1\162\10\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\20\0"+
    "\1\250\37\0\3\5\1\0\7\5\1\251\2\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\14\0\1\252\43\0\3\5\1\0\3\5\1\253"+
    "\6\5\1\0\3\5\5\0\1\5\2\0\1\5\1\0"+
    "\4\5\4\0\2\5\25\0\1\254\41\0\1\255\43\0"+
    "\3\5\1\0\3\5\1\256\6\5\1\0\3\5\5\0"+
    "\1\5\2\0\1\5\1\0\4\5\4\0\2\5\12\0"+
    "\1\257\45\0\3\5\1\0\1\5\1\260\10\5\1\0"+
    "\3\5\5\0\1\5\2\0\1\5\1\0\4\5\4\0"+
    "\2\5\13\0\1\261\47\0\2\262\61\0\1\263\44\0"+
    "\1\264\44\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5633];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\22\1\1\11\1\1\2\11\4\1\4\11"+
    "\2\1\2\11\1\0\1\11\14\1\1\11\1\1\2\0"+
    "\3\1\1\0\1\1\2\0\10\1\1\0\5\1\1\0"+
    "\4\1\2\0\1\11\4\1\2\11\4\1\1\0\3\1"+
    "\1\0\1\1\1\0\3\1\1\0\4\1\2\0\4\1"+
    "\2\0\4\1\1\0\1\1\1\0\1\1\4\0\3\1"+
    "\2\0\3\1\1\11\1\0\2\1\1\0\1\1\1\11"+
    "\1\1\1\0\1\11\2\0\1\11\1\1\1\0\2\1"+
    "\2\11\1\1\1\0\1\1\1\0\1\1\1\11\1\1"+
    "\1\11\2\0\1\1\1\0\1\1\1\11\1\1\1\0"+
    "\1\11\1\1\1\11\1\1\3\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[180];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    System.out.println("<" + sym.terminalNames[type] + ">");
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    System.out.println("<" + sym.terminalNames[type] + ", " + (String)value + ">");
    return new Symbol(type, yyline, yycolumn, value);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 206) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.out.println("Error en la linea: " + yyline+1 + " Col " + yycolumn+1 + " El texto fue " + yytext());
            }
          case 43: break;
          case 2: 
            { return symbol(sym.INTEGER ,yytext());
            }
          case 44: break;
          case 3: 
            { return symbol(sym.ID ,yytext());
            }
          case 45: break;
          case 4: 
            { string.setLength(0); yybegin(STRING);
            }
          case 46: break;
          case 5: 
            { return symbol(sym.RELATIONALOPERATOR, yytext());
            }
          case 47: break;
          case 6: 
            { return symbol(sym.MULTIPLICATIONOPERATOR, yytext());
            }
          case 48: break;
          case 7: 
            { return symbol(sym.ADDITIONOPERATOR, yytext());
            }
          case 49: break;
          case 8: 
            { 
            }
          case 50: break;
          case 9: 
            { return symbol(sym.OPENPARENTHESIS);
            }
          case 51: break;
          case 10: 
            { return symbol(sym.CLOSEPARENTHESIS);
            }
          case 52: break;
          case 11: 
            { return symbol(sym.PERIOD);
            }
          case 53: break;
          case 12: 
            { return symbol(sym.COMA);
            }
          case 54: break;
          case 13: 
            { return symbol(sym.ENDOFLINE);
            }
          case 55: break;
          case 14: 
            { string.append(yytext());
            }
          case 56: break;
          case 15: 
            { yybegin(YYINITIAL);
                              return symbol(sym.STRING, string.toString());
            }
          case 57: break;
          case 16: 
            { return symbol(sym.IF);
            }
          case 58: break;
          case 17: 
            { return symbol(sym.TO);
            }
          case 59: break;
          case 18: 
            { return symbol(sym.OR);
            }
          case 60: break;
          case 19: 
            { return symbol(sym.AS);
            }
          case 61: break;
          case 20: 
            { return symbol(sym.NOT);
            }
          case 62: break;
          case 21: 
            { return symbol(sym.AND);
            }
          case 63: break;
          case 22: 
            { return symbol(sym.SUB);
            }
          case 64: break;
          case 23: 
            { return symbol(sym.CHAR ,yytext());
            }
          case 65: break;
          case 24: 
            { return symbol(sym.DIM);
            }
          case 66: break;
          case 25: 
            { return symbol(sym.MOD);
            }
          case 67: break;
          case 26: 
            { return symbol(sym.FOR);
            }
          case 68: break;
          case 27: 
            { return symbol(sym.NEXT);
            }
          case 69: break;
          case 28: 
            { return symbol(sym.THEN);
            }
          case 70: break;
          case 29: 
            { return symbol(sym.ELSE);
            }
          case 71: break;
          case 30: 
            { return symbol(sym.TYPE ,yytext());
            }
          case 72: break;
          case 31: 
            { return symbol(sym.WHILEEND);
            }
          case 73: break;
          case 32: 
            { return symbol(sym.PRINT);
            }
          case 74: break;
          case 33: 
            { return symbol(sym.WHILE);
            }
          case 75: break;
          case 34: 
            { return symbol(sym.ENDIF);
            }
          case 76: break;
          case 35: 
            { return symbol(sym.ELSEIF);
            }
          case 77: break;
          case 36: 
            { return symbol(sym.STATIC);
            }
          case 78: break;
          case 37: 
            { return symbol(sym.PUBLIC);
            }
          case 79: break;
          case 38: 
            { return symbol(sym.ENDSUB);
            }
          case 80: break;
          case 39: 
            { return symbol(sym.PRIVATE);
            }
          case 81: break;
          case 40: 
            { return symbol(sym.READLINE);
            }
          case 82: break;
          case 41: 
            { return symbol(sym.FUNCTION);
            }
          case 83: break;
          case 42: 
            { return symbol(sym.ENDFUNCTION);
            }
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexer [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Lexer scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Lexer(reader);
          while ( !scanner.zzAtEOF ) scanner.next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
