import java.util.*;

public class SubdomainVisitCount {
    public static void main(String[] args) {
        String[] domains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        System.out.println(subdomainVisits2(domains));
    }

    // ***************** 1st Method ******************
    // Approach 1: Split each string in the array by blank space or . -> String[] sb
    // sb[0] will be number, sb[sb.len-1] is top-level domain, sb[sb.len-2] is main domain
    // If sb.len = 4, there will be top-level domain & main domain to keep track of occurrence
    // If sb.len = 3, consider only the top-level domain like (com) & full address
    // Map those strings to sb[0] => map. Then get data from map to list<string>
    // Runtime  : 36ms        -> + 10.17%
    // Memory   : 46.11MB     -> + 6.00%
    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> ls = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();

        for(String s : cpdomains) {
            String[] sb = s.split("\\s|\\.");
            int n = Integer.valueOf(sb[0]);

            if(sb.length == 4) {
                map.put(sb[3], map.getOrDefault(sb[3], 0) + n);
                map.put(sb[2] + "." + sb[3], map.getOrDefault(sb[2] + "." + sb[3], 0) + n);
                map.put(sb[1] + "." + sb[2] + "." + sb[3], map.getOrDefault(sb[1] + "." + sb[2] + "." + sb[3], 0) + n);
            } else {
                // last element: i.e com
                map.put(sb[2], map.getOrDefault(sb[2], 0) + n);

                // all combine
                map.put(sb[1] + "." + sb[2], map.getOrDefault(sb[1] + "." + sb[2], 0) + n);
            }
        }

        // Iterate over the entries of the map
        for (var entry : map.entrySet()) {
            // Concatenate key and value into a string
            // Add the combined string to the list
            ls.add(entry.getValue() + " " + entry.getKey());
        }
        return ls;
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Loop each string s in String[] cpdomains
    // Get index i of " " and use s.substring(0, i) => number
    // Inner Loop from i to s.len, if at i = ' ' or '.', get subString(i+1) => add to map
    // Runtime  : 17ms        -> + 70.87%
    // Memory   : 45.17MB     -> + 76.20%
    public static List<String> subdomainVisits2(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            int i = s.indexOf(" ");
            int v = Integer.parseInt(s.substring(0, i));
            for (; i < s.length(); ++i) {
                if (s.charAt(i) == ' ' || s.charAt(i) == '.') {
                    String t = s.substring(i + 1); // get the rest of s after ' ' or '.'
                    map.put(t, map.getOrDefault(t, 0) + v);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (var e : map.entrySet()) {
            ans.add(e.getValue() + " " + e.getKey());
        }
        return ans;
    }
    //  ***************** End of 2nd Method ******************

    // ***************** 3rd Method ******************
    // Approach 3: Optimized of 1st method by spliting cpd in cpdomains when meets ' '
    // So split[0] -> number, split[1] -> the rest of domains, which should be added to list
    // Then split the full domains by '.', then add subString(i+1) -> main domain (mail.com)
    // If there is another '.', then similarly add subString(i+1) -> top-level domain (.com)
    // Runtime  : 11ms        -> + 96.62%
    // Memory   : 45.51MB     -> + 32.12%
    public static List<String> subdomainVisits3(String[] cpdomains) {
        Map<String,Integer> countMap = new HashMap();
        for(String cpd: cpdomains) {
            String[] splits = cpd.split(" ");
            int num = Integer.parseInt(splits[0]);
            String domain = splits[1];

            int count = countMap.getOrDefault(domain, 0);
            count += num;
            countMap.put(domain, count);

            int i = 0;
            while(i < domain.length()) {
                if(domain.charAt(i) == '.') {
                    String parentDomain = domain.substring(i+1);
                    count = countMap.getOrDefault(parentDomain, 0);
                    count += num;
                    countMap.put(parentDomain, count);
                }
                i++;
            }
        }
        List<String> result = new ArrayList();
        for(Map.Entry<String,Integer> entry: countMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getValue()).append(" ").append(entry.getKey());
            result.add(sb.toString());
        }
        return result;
    }
    //  ***************** End of 3rd Method ******************
}
