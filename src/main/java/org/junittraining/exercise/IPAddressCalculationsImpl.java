package org.junittraining.exercise;

public class IPAddressCalculationsImpl implements IPAddressCalculations {

    @Override
    public long ipAddressToIPNumber(String ipAddress) throws IllegalArgumentException {
        // TODO: add your implementation here
        if(ipAddress == null) {
            throw new IllegalArgumentException("Null értéket kaptam.");
        } else {
            char[] split_IP = ipAddress.toCharArray();
            for(char character : split_IP) {
                if(character == ':') {
                    throw new IllegalArgumentException("IPv6 címet adtál meg, itt csak IPv4 szabad!");
                }
            }
        }

        final long FIRST_IP_BYTE = 16777216;
        final long SECOND_IP_BYTE = 65536;
        final long THIRD_IP_BYTE = 256;

        String[] blocks = ipAddress.trim().split("\\.");
        if(blocks.length != 4) {
            //System.out.println(ipAddress.length());
            throw new IllegalArgumentException("Forma nem egyezik meg az alábbival: w.x.y.z");
        } else {
            for(String block : blocks) {
                if(Integer.parseInt(block) > 255 || Integer.parseInt(block) < 0) {
                    throw new IllegalArgumentException("A blockban szerepli egyik érték kisebb nullánál,vagy nagyobb 255-nél");
                }
            }
        }

        return FIRST_IP_BYTE * Long.parseLong(blocks[0]) + SECOND_IP_BYTE * Long.parseLong(blocks[1]) + THIRD_IP_BYTE * Long.parseLong(blocks[2]) + Long.parseLong(blocks[3]);
    }
    
}
