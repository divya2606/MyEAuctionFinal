import axios from 'axios';

const BIDS_DETAILS_URL = "http://productquery-env.eba-jtmebmmu.ap-south-1.elasticbeanstalk.com/api/v1/productLookup/byId";
const PRODUCT_IDS_URL = "http://productquery-env.eba-jtmebmmu.ap-south-1.elasticbeanstalk.com/api/v1/productLookup/getProductIds";
const PRODUCT_DETAILS_URL = "http://productquery-env.eba-jtmebmmu.ap-south-1.elasticbeanstalk.com/api/v1/productLookup/getProductDetailsById"

class ProductService {
    getProductIds() {
        return axios.get(PRODUCT_IDS_URL);
    }

    getBidsByProductId(productId) {
        return axios.get(BIDS_DETAILS_URL + "/" + productId);
    }

    getProductDetails(productId) {
        console.log("INSIDE getProductDetails -- productId = " + productId);
        return axios.get(PRODUCT_DETAILS_URL + "/" + productId);
    }
}

export default new ProductService()