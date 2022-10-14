import React, { Component } from 'react';
import ProductService from '../services/ProductService';
import Select from 'react-select';

class ProductDetailsComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedOption: "",
            productIds: [],
            products: [],
            bids: []
        }
        this.onChangeInput = this.onChangeInput.bind(this);
        this.getDetails = this.getDetails.bind(this);
    }

    /*onChangeInput(value) {
        console.log(value);
    }*/

    onChangeInput = e => {
        this.setState({ selectedOption: e ? e.value : '' });
    };

    getDetails() {
        console.log("selectedvalue " + this.state.selectedOption);
        ProductService.getProductDetails(this.state.selectedOption).then((res) => {
            this.setState({products: res.data.products});
        });
        ProductService.getBidsByProductId(this.state.selectedOption).then((res) => {
            this.setState({bids: res.data.bids});
        });
    }

    componentDidMount() {
        ProductService.getProductIds().then((res) => {
            this.setState({productIds: res.data})
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <h2 className = "text-center">Product Details</h2>
                <div className = "row">
                    <br></br>
                    <div className = "col-md-2">
                        <label className = "text-center"> Product </label>
                    </div>
                    <div className = "col-md-6">
                        <Select name = "pids" 
                        options = { this.state.productIds.map(d => ({label: d, value: d})) } 
                        onChange = {this.onChangeInput}
                        value={this.state.productIds.find(item => item.value === this.state.selectedOption)}/>
                    </div>
                    <div className = "col-md-2">
                    <button className="btn btn-info" onClick={this.getDetails}>GET</button>
                    </div>
                </div>
                <br></br>
                <div className = "col-md-12">
                    <div className = "row">
                        <div className = "table-responsive">
                            <table>
                                <tbody>
                                {
                                    this.state.products && this.state.products.length > 0 ? this.state.products.map(pdt =>
                                    //this.state.products.map(pdt =>
                                    <tr>
                                    <tr>
                                        <td><label>Product Name</label></td>
                                        <td><input name="productName" value={pdt.productName}/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Short Description</label></td>
                                        <td><input name="shortDesc" value={pdt.shortDesc}/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Detailed Description</label></td>
                                        <td><input name="detailedDesc" value={pdt.detailedDesc}/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Category</label></td>
                                        <td><input name="category" value={pdt.category}/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Start Price</label></td>
                                        <td><input name="startPrice" value={pdt.startPrice}/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Bid End Date</label></td>
                                        <td><input name="bidEndDate" value={pdt.bidEndDate}/></td>
                                    </tr>
                                    </tr>
                                ) : 
                                <tr>
                                <tr>
                                    <td><label>Product Name</label></td>
                                    <td><input name="productName"/></td>
                                </tr>
                                <tr>
                                    <td><label>Short Description</label></td>
                                    <td><input name="shortDesc"/></td>
                                </tr>
                                <tr>
                                    <td><label>Detailed Description</label></td>
                                    <td><input name="detailedDesc"/></td>
                                </tr>
                                <tr>
                                    <td><label>Category</label></td>
                                    <td><input name="category"/></td>
                                </tr>
                                <tr>
                                    <td><label>Start Price</label></td>
                                    <td><input name="startPrice"/></td>
                                </tr>
                                <tr>
                                    <td><label>Bid End Date</label></td>
                                    <td><input name="bidEndDate"/></td>
                                </tr>
                                </tr>
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <br></br>
                <div className = "row">
                    <h2 className = "text-center">Bids List</h2>
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Bid Amount</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.bids && this.state.bids.length > 0 ? this.state.bids.map(
                                    bid =>
                                    <tr key = {bid.buyerEmail}>
                                        <td> {bid.bidAmount} </td>
                                        <td> {bid.buyerFName} </td>
                                        <td> {bid.buyerEmail} </td>
                                        <td> {bid.buyerPhone} </td>
                                    </tr>
                                ) : null
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ProductDetailsComponent;