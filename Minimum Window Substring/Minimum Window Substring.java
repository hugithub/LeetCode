public class Solution {
    public String minWindow(String S, String T) {
        int[] found = new int[256];
        int[] toFind = new int[256];
        for (int i = 0; i < T.length(); i++)
            toFind[T.charAt(i)]++;

        int minLength = S.length() + 1, start = 0, end = -1;
        for (int i = 0, j = 0, missing = T.length(); i < S.length(); i++) {
            if (++found[S.charAt(i)] <= toFind[S.charAt(i)])
                missing--;

            if (missing == 0) {
                while (missing == 0) {
                    if (--found[S.charAt(j)] < toFind[S.charAt(j++)])
                        missing++;
                }

                if (i + 1 - (j - 1) < minLength) {
                    start = j - 1;
                    end = i;
                    minLength = i + 1 - (j - 1);
                }
            }
        }

        return S.substring(start, end + 1);
    }
}

class Solution2 {
    class Window {
        HashMap<Character, Integer> target, current;
        int missing;

        Window(String S) {
            target = new HashMap<Character, Integer>();
            current = new HashMap<Character, Integer>();
            missing = S.length();
            for (int i = 0; i < S.length(); i++)
                target.put(S.charAt(i), target.get(S.charAt(i)) == null ? 1 : target.get(S.charAt(i)) + 1);
        }

        int add(Character c) {
            if (!target.containsKey(c))
                return missing;
            current.put(c, current.get(c) == null ? 1 : current.get(c) + 1);
            missing -= current.get(c) <= target.get(c) ? 1 : 0;
            return missing;
        }

        int remove(Character c) {
            if (!target.containsKey(c))
                return missing;
            current.put(c, current.get(c) - 1);
            missing += current.get(c) >= target.get(c) ? 0 : 1;
            return missing;
        }
    }

    public String minWindow(String S, String T) {
        Window w = new Window(T);
        int ms = 0, me = S.length();
        int start = 0, end = 0;
        while (end < S.length()) {
            if (w.add(S.charAt(end)) == 0) {
                while (start <= end) {
                    if (end - start < me - ms) {
                        me = end;
                        ms = start;
                    }
                    if (w.remove(S.charAt(start++)) > 0)
                        break;
                }
            }
            end++;
        }

        return ms == 0 && me == S.length() ? "" : S.substring(ms, me + 1);
    }
}
