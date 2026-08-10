import axios from "axios";
import { useState } from "react";

// --- Inline Style Objects ---
const styles = {
  wrapper: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    minHeight: "100vh",
    backgroundColor: "#f4f7f6",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    padding: "20px",
  },

  container: {
    width: "100%",
    maxWidth: "550px",
    backgroundColor: "#ffffff",
    padding: "35px",
    borderRadius: "10px",
    boxShadow: "0 4px 15px rgba(0, 0, 0, 0.1)",
    boxSizing: "border-box",
  },

  heading: {
    color: "#2c3e50",
    marginTop: "0",
    marginBottom: "25px",
    fontSize: "22px",
    fontWeight: "600",
    borderBottom: "2px solid #f1f3f5",
    paddingBottom: "12px",
  },

  form: {
    display: "flex",
    flexDirection: "column",
    gap: "18px",
  },

  label: {
    fontSize: "14px",
    fontWeight: "600",
    color: "#5f6368",
    marginBottom: "-10px",
  },

  select: {
    padding: "10px 12px",
    backgroundColor: "#f8f9fa",
    border: "1px solid #cccccc",
    borderRadius: "6px",
    fontSize: "15px",
    color: "#333333",
    outline: "none",
    cursor: "pointer",
  },

  input: {
    padding: "12px 15px",
    border: "1px solid #cccccc",
    borderRadius: "6px",
    fontSize: "15px",
    outline: "none",
    color: "#333333",
    backgroundColor: "#ffffff",
  },

  textarea: {
    padding: "12px 15px",
    border: "1px solid #cccccc",
    borderRadius: "6px",
    fontSize: "15px",
    outline: "none",
    color: "#333333",
    backgroundColor: "#ffffff",
    minHeight: "150px",
    resize: "vertical",
    fontFamily: "inherit",
  },

  submitBtn: {
    backgroundColor: "#007bff",
    color: "white",
    padding: "12px",
    fontSize: "16px",
    fontWeight: "bold",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    transition: "background-color 0.2s ease",
  },
};

function EmailComposer() {

  const [form, setForm] = useState({
    to: "",
    subject: "",
    body: "",
    module: "LEAD",
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const sendEmail = async (e) => {

    e.preventDefault();

    const token = localStorage.getItem("token");

    // Check JWT token
    if (!token) {
      alert("Please login first.");
      return;
    }

    const params = new URLSearchParams();

    params.append("to", form.to);
    params.append("subject", form.subject);
    params.append("body", form.body);
    params.append("module", form.module);

    try {

      const response = await axios.post(
        "https://crm-system-production-9f24.up.railway.app/api/crm/email/send",
        params,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      );

      console.log("Email Response:", response.data);

      alert("Email sent successfully!");

      setForm({
        to: "",
        subject: "",
        body: "",
        module: "LEAD",
      });

    } catch (error) {

      console.error("Email Error:", error);

      if (error.response) {

        console.error(
          "Status:",
          error.response.status
        );

        console.error(
          "Response:",
          error.response.data
        );

        if (error.response.status === 401) {
          alert("Session expired. Please login again.");
        }

        else if (error.response.status === 403) {
          alert("Access denied. Please login again.");
        }

        else if (error.response.status === 500) {
          alert("Server error. Check backend email/SMTP configuration.");
        }

        else {
          alert(
            `Failed to send email. Error: ${error.response.status}`
          );
        }

      } else {

        alert("Network error. Please check backend connection.");
      }
    }
  };

  return (
    <div style={styles.wrapper}>

      <div style={styles.container}>

        <h2 style={styles.heading}>
          CRM Email Module
        </h2>

        <form
          style={styles.form}
          onSubmit={sendEmail}
        >

          {/* CRM Module */}
          <span style={styles.label}>
            Related CRM Module
          </span>

          <select
            style={styles.select}
            name="module"
            value={form.module}
            onChange={handleChange}
          >
            <option value="LEAD">
              Lead
            </option>

            <option value="TICKET">
              Ticket
            </option>

            <option value="CUSTOMER">
              Customer
            </option>
          </select>

          {/* Recipient */}
          <input
            style={styles.input}
            type="email"
            name="to"
            placeholder="Recipient Email"
            value={form.to}
            onChange={handleChange}
            required
          />

          {/* Subject */}
          <input
            style={styles.input}
            type="text"
            name="subject"
            placeholder="Subject"
            value={form.subject}
            onChange={handleChange}
            required
          />

          {/* Email Body */}
          <textarea
            style={styles.textarea}
            name="body"
            placeholder="Email Content..."
            value={form.body}
            onChange={handleChange}
            required
          />

          {/* Send Button */}
          <button
            type="submit"
            style={styles.submitBtn}
          >
            Send Email
          </button>

        </form>

      </div>

    </div>
  );
}

export default EmailComposer;