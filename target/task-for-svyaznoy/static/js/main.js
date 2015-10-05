var SmsForm = React.createClass({
    render: function () {
        var inlineStyle = {
            display : 'inline-block'
        };
        var inlineMarginStyle = {
            display : 'inline-block',
            margin: '1em'
        };
        return (
            <form onSubmit={this.props.submitButtonClickListener}>
                <div style={inlineStyle}>
                    <label forHtml='phone'>Номер телефона</label>
                    <input ref='phone' name='phone' type='text' value={this.props.sms.phone} onChange={this.onChange}/>
                </div>
                <div style={inlineMarginStyle}>
                    <label forHtml='message'>Сообщение</label>
                    <input ref='message' name='message' type='text' value={this.props.sms.message} onChange={this.onChange}/>
                </div>
                <div style={inlineMarginStyle}>
                    <input type='submit' value="Отправить" />
                </div>
            </form>
        );
    },
    onChange: function () {
        var phone = React.findDOMNode(this.refs.phone).value;
        var message = React.findDOMNode(this.refs.message).value;
        this.props.handleChange(phone, message);
    }
});

var SmsTableRow = React.createClass({
    render: function () {
        return (
            <tr>
                <td>{this.props.sms.phone}</td>
                <td>{this.props.sms.sentDate}</td>
                <td>{this.props.sms.sentStatus}</td>
                <td>{this.props.sms.message}</td>
            </tr>
        );
    }
});

var SmsTable = React.createClass({
    render: function () {
        var rows = [];
        this.props.sms.forEach(function (sms) {
            rows.push(<SmsTableRow sms={sms} />);
        }.bind(this));
        return (
            <table>
                <thead>
                    <tr>
                        <th>Phone</th>
                        <th>Sent date</th>
                        <th>Sent Status</th>
                        <th>Message</th>
                    </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>
        );
    }
});

var SmsPanel = React.createClass({
    getInitialState: function () {
        return {
            sms: [],
            smsToSend: {
                phone: "",
                message: ""
            }
        }
    },
    componentDidMount: function () {
        this.loadSms();
    },
    render: function () {
        return(
            <div className="row">
                <div className="one-half column"></div>
                <SmsForm submitButtonClickListener={this.submitButtonClickListener}
                         handleChange={this.handleChange}
                         sms={this.state.smsToSend}/>
                <SmsTable sms={this.state.sms} />
            </div>
        );
    },
    loadSms: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({
                    sms: data
                });
            }.bind(this)
        })
    },
    handleChange: function (phone, message) {
        this.setState({
            smsToSend: {
                phone: phone,
                message: message
            }
        });
    },
    submitButtonClickListener: function (e) {
        e.preventDefault();
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(this.state.smsToSend),
            cache: false,
            success: function(data) {
                this.loadSms();
                this.setState({
                    smsToSend: {}
                });
            }.bind(this),
            error: function (xhr, status, err) {
            }.bind(this)
        });
    }
});
React.render(<SmsPanel url='/api/sms'/>, document.getElementById('content'));

